package com.pedro0210.hobbylobby.presentation.viewmodel.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.repository.CreatorRepo
import com.pedro0210.hobbylobby.data.repository.ProfileRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import com.pedro0210.hobbylobby.presentation.state.ProfileState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CreatorViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val user: UserData,
    private val repo: ProfileRepo
): ViewModel() {
    private val _state = MutableStateFlow(ProfileCreationState())
    val state = _state.asStateFlow()

    init {
        onLoadUser()
        println(state.value.imageUrl)

    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.onDoneEditing -> onDoneEditing(event.name, event.bio, event.image, event.social)
            is ProfileEvent.onLoadUser -> onLoadUser()
            is ProfileEvent.onNameChange -> onNameChange(event.name)
            is ProfileEvent.onBioChange -> onBioChange(event.bio)
            is ProfileEvent.onPictureChange -> onPictureChange(event.image)
            is ProfileEvent.onSocialMediaCreate -> onSocialMediaCreate(event.socialName, event.url, event.image)
            is ProfileEvent.onSocialMediaDelete -> onSocialMediaDelete(event.socialName)
            is ProfileEvent.onSocialNameChange -> onSocialNameChange(event.socialName)
            is ProfileEvent.onSocialUrlChange -> onSocialUrlChange(event.url)
            is ProfileEvent.onSocialImageChange -> onSocialImageChange(event.image)

        }
    }

    private fun onSocialMediaDelete(socialName: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    hasError = false
                )
            }
            try {
                val socials = _state.value.socials.filter { it.name != socialName }
                val userID = user.getId().first()
                repo.deleteSocialMedia(userID, socialName)
                _state.update {
                    it.copy(
                        socials = socials,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
        }

    }

    private fun onDoneEditing(name: String, bio: String, image: Uri?, social: List<SocialMediaCreation>) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    hasError = false,
                    isDoneUploading = false
                )
            }
            try {
                val userID = user.getId().first()
                if (state.value.hasChangedPfp) {
                    repo.changeUserInfoWImage(userID, name, bio, image!!)
                } else {
                    repo.changeUserInfoWOImage(userID, name, bio)
                }
                val newSocials = social.filter { it.new == true }
                if(newSocials.isNotEmpty()) {
                    repo.addSocialMedia(userID, newSocials)
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        isDoneUploading = true
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
        }
    }

    private fun onLoadUser(){
        viewModelScope.launch {
            val userID = user.getId().first()
            val profile = repo.getUser(userID)
            _state.update {
                it.copy(
                    username = profile.name,
                    bio = profile.description,
                    imageUrl = profile.imageUrl,
                    socials = profile.socialMedia,
                    hasChangedPfp = false

                )
            }
        }
    }

    private fun onNameChange(name: String) {
        _state.update { it.copy(
            username = name
        )}
    }

    private fun onBioChange(bio: String) {
        _state.update { it.copy(
            bio = bio
        )}
    }

    private fun onPictureChange(image: Uri?) {
        _state.update { it.copy(
            image = image,
            hasChangedPfp = true
        )}
    }

    private fun onSocialNameChange(socialName: String) {
        _state.update { it.copy(
            socialName = socialName
        )}
    }

    private fun onSocialUrlChange(url: String) {
        _state.update { it.copy(
            socialUrl = url
        )}
    }

    private fun onSocialImageChange(image: Uri?) {
        _state.update { it.copy(
            socialImage = image
        )}
    }

    private fun onSocialMediaCreate(socialName: String, url: String, image: Uri?) {
        _state.update { it.copy(
            isLoading = true,
            hasError = false
        )}
        viewModelScope.launch {
            try {
                val newSocial = SocialMediaCreation(
                    name = socialName,
                    url = url,
                    image = image,
                    new = true,
                    imageUrl = ""
                )
                val socials = _state.value.socials.toMutableList().apply { add(newSocial) }

                _state.update { it.copy(
                    socials = socials,
                    isLoading = false
                )}
                println(state.value.socials)
            } catch (e: Exception) {
                println("Error: $e")
                _state.update { it.copy(
                    isLoading = false,
                    hasError = true
                )}
            }
        }
    }

    companion object{

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                ProfileViewModel(
                    user = UserData(application.dataStore),
                    repo = ProfileRepo()
                )
            }
        }
    }





    //TODO: field, pfp, user, social
    //TODO: collection social with the list of social media added

}