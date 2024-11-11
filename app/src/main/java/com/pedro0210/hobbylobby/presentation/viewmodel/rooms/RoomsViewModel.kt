package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import com.pedro0210.hobbylobby.presentation.state.RoomScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class RoomsViewModel(
    roomId: String,
    roomName: String,
    roomDescription: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        RoomScreenState(
            roomName = roomName,
            roomDescription = roomDescription,
            users = listOf(
                RoomMember("Juan", "Conectado", R.drawable.avatar),
                RoomMember("Abby", "18 min", R.drawable.avatar),
                RoomMember("Oscar", "Me gusta Pokémon", R.drawable.avatar),
                RoomMember("Name", "18 min", R.drawable.avatar),
                RoomMember("Name", "18 min", R.drawable.avatar)
            )
        )
    )
    val uiState: StateFlow<RoomScreenState> get() = _uiState

    fun joinRoom() {
        _uiState.update { currentState ->
            currentState.copy(isJoined = true)
        }
    }

    companion object {
        fun provideFactory(id: String, roomName: String, roomDescription: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RoomsViewModel(id, roomName, roomDescription)
            }
        }

    }
}

