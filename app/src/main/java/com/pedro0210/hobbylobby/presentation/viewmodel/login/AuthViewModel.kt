package com.pedro0210.hobbylobby.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.repository.AuthRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.domain.util.LoginEnum
import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val preferences: UserData,
    private val loginRepo: AuthRepo
): ViewModel() {
    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState = _loginState.asStateFlow()

    private val _profileCreationState = MutableStateFlow(ProfileCreationState())
    val profileCreationState = _profileCreationState.asStateFlow()


    init {
        viewModelScope.launch {
            preferences.getAuth().collect { authState ->
                _loginState.update {
                    it.copy(isLogged = authState)
                }
            }
        }
    }

    //For Logging Screen
    fun changeEmail(email: String) {
        _loginState.update {
            it.copy(
                email = email
            )
        }
    }

    fun changePassword(password: String) {
        _loginState.update {
            it.copy(
                password = password
            )
        }
    }

    fun checkBox(value: Boolean){
        if (value) {
            _loginState.update {
                it.copy(
                    boxChecked = true
                )
            }
        }
        else{
            _loginState.update {
                it.copy(
                    boxChecked = false
                )
            }
        }
    }

    fun changeButtonText(buttonTittle: String){
        _loginState.update{
            it.copy(
                buttonText = buttonTittle
            )
        }
    }

    fun changeNavDestination(destination: AuthDestionation){
        _loginState.update {
            it.copy(
                navDestination = destination
            )
        }
    }

    private fun changeError(error: Boolean){
            _loginState.update {
                it.copy(
                    hasError = error
                )
            }

    }

    //TODO: set login enum to the type of login
    //TODO: set diferent types of loging depending on the enum
    fun login(type: LoginEnum) {
        viewModelScope.launch {
            val id = loginRepo.manageAuth(loginState.value.email, loginState.value.password)
            val username = loginRepo.getUsername(id)
            val pfp = loginRepo.getPfp(id)

            if (id != "ERROR") {
                changeError(false)
                preferences.logIn(
                    id = id,
                    username = username,
                    pfp = pfp
                )
            }
            else {
                println("not logged in")
                changeError(true)
            }
        }
    }


    //For Logging Screen


    //For Profile Creation Screen
    fun changeUsername(username: String) {
        _profileCreationState.update{
            it.copy(
                username = username
            )
        }
    }






    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                AuthViewModel(
                    preferences = UserData(application.dataStore),
                    loginRepo = AuthRepo()
                )
            }
        }
    }
}
