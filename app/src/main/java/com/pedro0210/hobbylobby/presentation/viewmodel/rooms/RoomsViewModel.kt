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
                RoomMember("Juan", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Cristo_abrazado_a_la_cruz_%28El_Greco%2C_Museo_del_Prado%29.jpg/640px-Cristo_abrazado_a_la_cruz_%28El_Greco%2C_Museo_del_Prado%29.jpg"),

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



