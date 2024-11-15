package com.pedro0210.hobbylobby.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.data.repository.ProfileRepository
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.state.ProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val preferences: UserPreferences,
    private val repository: ProfileRepository
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()


    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.onLoadUser -> onLoadUser(event.ID)
            ProfileEvent.onLogoutClick -> onLogoutClick()
            is ProfileEvent.onDoneAdding -> onSocialAdd(event.socialMedia)
        }
    }

    private fun onLoadUser(ID: Int) {
        viewModelScope.launch {
            val screenState = _state.value

            _state.update { it.copy(
                user = repository.getProfile1(ID),
                isLoading = true,
                hasError = false
            )}

            delay(2000)

            if (!_state.value.hasError){
                _state.update { it.copy(
                    isLoading = false,
                    isSuccess = true,
                    hasError = false
                )}

            }


        }
    }

    private fun onLogoutClick() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }

    private fun onSocialAdd(social: SocialMedia) {
        viewModelScope.launch {
            _state.update { it.copy(
                user = _state.value.user?.socialMedia?.plus(social)?.let { it1 ->
                    _state.value.user?.copy(
                        socialMedia = it1
                    )
                }
            )}
        }
    }

    companion object {
        fun provideFactory() : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                ProfileViewModel(
                    UserPreferences(application.dataStore),
                    ProfileRepository()
                )
            }
        }
    }

    //TODO: field, pfp, user, social
    //TODO: collection social with the list of social media added

}