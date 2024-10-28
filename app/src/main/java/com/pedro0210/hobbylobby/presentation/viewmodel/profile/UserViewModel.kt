package com.pedro0210.hobbylobby.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.event.UserEvent
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.state.ProfileState
import com.pedro0210.hobbylobby.presentation.state.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: ProfileRepository
): ViewModel() {
    private val _state = MutableStateFlow(UserState())
    val state = _state.asStateFlow()


    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.onLoadUser -> onLoadUser(event.ID)
        }
    }

    private fun onLoadUser(ID: Int) {
        viewModelScope.launch {
            val screenState = _state.value

            _state.update { it.copy(
                user = repository.getProfile2(ID),
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



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel(
                    ProfileRepository()
                )
            }
        }
    }

}