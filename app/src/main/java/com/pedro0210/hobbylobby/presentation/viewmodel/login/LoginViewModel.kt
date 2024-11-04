package com.pedro0210.hobbylobby.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.data.repository.LoginRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.domain.util.LoginEnum
import com.pedro0210.hobbylobby.presentation.navigation.Auth
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import com.pedro0210.hobbylobby.presentation.util.LoginNavEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferences: UserPreferences,
    private val loginRepo: LoginRepo
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

    fun checkBox(value: Boolean){
        if (value) {
            _state.update {
                it.copy(
                    boxChecked = true
                )
            }
        }
        else{
            _state.update {
                it.copy(
                    boxChecked = false
                )
            }
        }
    }

    fun changeButtonText(buttonTittle: String){
        _state.update{
            it.copy(
                buttonText = buttonTittle
            )
        }
    }

    fun changeNavDestination(destination: Auth){
        _state.update {
            it.copy(
                navDestination = destination
            )
        }
    }

    //TODO: set login enum to the type of login
    fun login(type: LoginEnum): Boolean {
        var loginSuccessful = false

        viewModelScope.launch {
            val id = loginRepo.login(state.value.email, state.value.password)

            if (id != "") {
                preferences.logIn(id)
                loginSuccessful = true
            }

            else {
                loginSuccessful = false
            }
        }
        return loginSuccessful
    }


    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                LoginViewModel(
                    preferences = UserPreferences(application.dataStore),
                    loginRepo = LoginRepo()
                )
            }
        }
    }
}
