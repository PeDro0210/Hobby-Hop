package com.pedro0210.hobbylobby.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
private val preferences: UserPreferences
    //TODO: add the repo here
): ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun changeEmail(email: String) {
        _state.update {
            it.copy(
                email = email
            )
        }
    }
    fun changePassword(password: String) {
        _state.update {
            it.copy(
                password = password
            )
        }
    }

    fun login() {
        //TODO: call the repo with the firebase implementation
       viewModelScope.launch {
           preferences.logIn("PLACEHOLDER_ID")
       }
    }


    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                LoginViewModel(
                    preferences = UserPreferences(application.dataStore)
                )
            }
        }
    }
}
