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
    roomId: String,
    repo: RoomsRepo,
    roomName: String,
    roomImage:String,
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


    private lateinit var users : StateFlow<List<RoomMember>>

    init{
        viewModelScope.launch {
            println(roomId)
            users = repo.getUsers(roomId)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
            launch {
                users.collect{ usersList ->
                    _uiState.update {
                        it.copy(
                            users = usersList
                        )
                    }
                }
            }
        }
    }

    fun joinRoom() {
        _uiState.update { currentState ->
            currentState.copy(isJoined = true)
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



