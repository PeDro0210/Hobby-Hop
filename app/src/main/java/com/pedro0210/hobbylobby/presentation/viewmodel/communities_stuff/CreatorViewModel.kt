package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.repository.CreatorRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.event.CreatorEvent
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.state.CreatorScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CreatorViewModel(
    private val user: UserData,
    private val repo: CreatorRepo
) : ViewModel() {

    private val _state = MutableStateFlow(CreatorScreenState())
    val state = _state.asStateFlow()



    fun onEvent(event: CreatorEvent) {
        when (event) {
            is CreatorEvent.onComunityCreate -> onCreateCommunity(event.title, event.description, event.image, event.rooms, event.countryName)
            is CreatorEvent.onRoomCreate -> onCreateRoom(event.title, event.description, event.image)
            is CreatorEvent.onRoomDelete -> onDeleteRoom(event.title)
            is CreatorEvent.onPictureChange -> onPictureChange(event.image)
            is CreatorEvent.onNameChange -> onNameChange(event.name)
            is CreatorEvent.onDescriptionChange -> onDescriptionChange(event.description)
            is CreatorEvent.onBigCommunityChange -> onBigCommunityChange(event.bigCommunityName)
            is CreatorEvent.onRoomPictureChange -> onRoomPictureChange(event.image)
            is CreatorEvent.onRoomNameChange -> onRoomNameChange(event.title)
            is CreatorEvent.onRoomDescriptionChange -> onRoomDescriptionChange(event.description)

        }
    }


    private fun onCreateCommunity(title: String, description: String, image: Uri?, rooms: List<CommunityCreation>, countryName: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            try {
                val bigCommunityId = repo.getBigCommunityId(countryName)//TODO: Implement the big community id, IDK where to get the name
                println("the big community id is $bigCommunityId")
                val isNameValid = repo.verifyComunityName(bigCommunityId, title)
                println("the name is valid $isNameValid")
                if (isNameValid) {

                    if (image != null) {
                        val communityId = repo.createCommunity(title, description, image, bigCommunityId)
                        println("the community id is $communityId")
                        val userId = user.getId().first()
                        println("the user id is $userId")
                        val statusRooms = repo.addrooms(rooms, communityId, userId, bigCommunityId) //TODO: FIX THIS
                        println("the status of the rooms is $statusRooms")
                        val statusAdminUser = repo.makeUserAdmin(userId, communityId, bigCommunityId )//TODO: FIX THIS
                        println("the status of the admin user is $statusAdminUser")

                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                hasError = true
                            )
                        }
                    }



                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
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

    private fun onNameChange(name: String) {
        _state.update { it.copy(
            title = name
        )}
    }

    private fun onRoomNameChange(title: String) {
        _state.update { it.copy(
            roomTitle = title
        )}
    }

    private fun onDescriptionChange(description: String) {
        _state.update { it.copy(
            description = description
        )}
    }

    private fun onRoomDescriptionChange(description: String) {
        _state.update { it.copy(
            roomDescription = description
        )}
    }

    private fun onBigCommunityChange(bigCommunityName: String) {
        _state.update { it.copy(
            bigCommunityName = bigCommunityName
        )}
    }

    private fun onPictureChange(image: Uri?) { //TODO: Implement the image upload thingy
        _state.update { it.copy(
            image = image
        )}
    }

    private fun onRoomPictureChange(image: Uri?) { //TODO: Implement the image upload thingy
        _state.update { it.copy(
            roomImage = image
        )}
    }

    private fun onCreateRoom(title: String, description: String, image: Uri?) {

        _state.update { it.copy(
            isLoading = true,
            hasError = false
        )}
        viewModelScope.launch {
            try {
                val newRoom = CommunityCreation(
                    title = title,
                    description = description,
                    image = image,
                    type = CommunityType.rooms,
                    partOfCommunity = true,
                    id = "N/A"
                )
                val rooms = _state.value.rooms.toMutableList().apply { add(newRoom) }

                _state.update { it.copy(
                    rooms = rooms,
                    isLoading = false
                )}
                println(state.value.rooms)
            } catch (e: Exception) {
                println("Error: $e")
                _state.update { it.copy(
                    isLoading = false,
                    hasError = true
                )}
            }
        }
    }

    private fun onDeleteRoom(title: String) {
        _state.update { it.copy(
            isLoading = true,
            hasError = false
        )}
        try {
            val rooms = _state.value.rooms.filter { it.title != title }
            _state.update { it.copy(
                rooms = rooms,
                isLoading = false
            )}
        } catch (e: Exception) {
            _state.update { it.copy(
                isLoading = false,
                hasError = true
            )}
        }
    }

    companion object{

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                CreatorViewModel(
                    user = UserData(application.dataStore),
                    repo = CreatorRepo()
                )
            }
        }
    }

    /*
    fun setName() {
        viewModelScope.launch {
            preferences.setName(_state.value.name)
        }
    }

    fun eraseName() {
        viewModelScope.launch {
            preferences.setName("")
        }
    }
     */


        //TODO: Implement the image upload thingy
        //To image reference profile creation SCreen LaunchedEffect, renderize uri
        //choseimage, authviwemodel
        //TODO: create a func to pickup all the cuminity data, and the rooms data that is a list         rooms: List<Community>
        //TODO: if the operation is not done, wait              Viewmodel STUFF
        //Reference LoginScreen LaunchedEffect, is loading state
}

