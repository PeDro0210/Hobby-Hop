package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.presentation.model.Request
import com.pedro0210.hobbylobby.presentation.state.AceptacionesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AceptacionesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AceptacionesScreenState())
    val uiState: StateFlow<AceptacionesScreenState> get() = _uiState

    init {

        _uiState.update { currentState ->
            currentState.copy(
                aceptaciones = listOf("Juan", "Anna", "Marcos", "Luis", "Carlos"),
                roomName = "Nombre de la sala",
                roomDescription = "Descripción de la sala"
            )
        }
    }

    fun acceptRequest(userName: String) {

        _uiState.update { currentState ->
            currentState.copy(aceptaciones = currentState.aceptaciones - userName)
        }
    }

    fun rejectRequest(userName: String) {

        _uiState.update { currentState ->
            currentState.copy(aceptaciones = currentState.aceptaciones - userName)
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AceptacionesViewModel()
            }
        }
    }
}
