package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.data.repository.RoomsRepo
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import com.pedro0210.hobbylobby.presentation.state.RoomScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RoomsViewModel(
    private val roomId: String,
    private val repo: RoomsRepo,
    roomName: String,
    roomImage: String,
    roomDescription: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        RoomScreenState(
            roomName = roomName,
            roomDescription = roomDescription,
            users = emptyList(),
            roomImage = roomImage
        )
    )
    val uiState = _uiState.asStateFlow()

    private lateinit var users: StateFlow<List<RoomMember>>

    init {
        viewModelScope.launch {

            users = repo.getUsers(roomId)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            launch {
                users.collect { usersList ->
                    _uiState.update {
                        it.copy(users = usersList)
                    }
                }
            }
        }
    }

    fun attemptJoinRoom(userId: String) {
        viewModelScope.launch {
            val success = repo.checkAndRequestJoin(roomId, userId)
            if (success) {
                _uiState.update {
                    it.copy(isRequestPending = true)
                }
            }
        }
    }

    fun checkRoomAcceptance(userId: String) {
        viewModelScope.launch {

            while (true) {
                val currentUsers = users.value
                val isAccepted = currentUsers.any { it.id == userId }

                if (isAccepted) {
                    _uiState.update {
                        it.copy(
                            isJoined = true,
                            isRequestPending = false
                        )
                    }
                    break
                }

                kotlinx.coroutines.delay(5000)
            }
        }
    }

    fun leaveRoom(userId: String) {
        viewModelScope.launch {
            val success = repo.removeUserFromRoom(roomId, userId)
            if (success) {
                _uiState.update {
                    it.copy(isJoined = false)
                }
            }

        }
    }


    companion object {
        fun provideFactory(
            id: String,
            roomName: String,
            roomDescription: String,
            roomImage: String
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RoomsViewModel(
                    repo = RoomsRepo(),
                    roomId = id,
                    roomName = roomName,
                    roomDescription = roomDescription,
                    roomImage = roomImage
                )
            }
        }
    }
}



