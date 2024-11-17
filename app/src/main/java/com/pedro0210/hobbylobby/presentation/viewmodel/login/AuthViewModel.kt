package com.pedro0210.hobbylobby.presentation.viewmodel.login


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.network.responseToResult
import com.pedro0210.hobbylobby.data.repository.AuthRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.domain.util.AuthAction
import com.pedro0210.hobbylobby.domain.util.LoginEnum
import com.pedro0210.hobbylobby.domain.util.NetworkError
import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.state.CreationState
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.pedro0210.hobbylobby.domain.util.Result

class AuthViewModel(
    private val user: UserData,
    private val repo: AuthRepo
): ViewModel() {
    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState = _loginState.asStateFlow()

    private val _profileCreationState = MutableStateFlow(CreationState())
    val profileCreationState = _profileCreationState.asStateFlow()


    init {
        viewModelScope.launch {
            user.getAuth().collect { authState ->
                _loginState.update {
                    it.copy(
                        isLogged = authState
                    )
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
                    boxChecked = true,
                    authAction = AuthAction.LOGIN
                )
            }


        }
        else{
            _loginState.update {
                it.copy(
                    boxChecked = false,
                    authAction = AuthAction.SIGN_UP
                )
            }
        }
    }

    fun changeButtonText(buttonTittle: String){
        _loginState.update{
            it.copy(
                buttonText = buttonTittle,

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


    private fun changeError(error: Boolean, message: String){
            _loginState.update {
                it.copy(
                    hasError = error,
                    errorMessage = message
                )
            }

    }

    //123123@gmail.com
    //123
    //TODO: set login enum to the type of login
    //TODO: set diferent types of loging depending on the enum
    fun login(type: AuthAction) {
        viewModelScope.launch {
            try {
                val result = when (type) {
                    AuthAction.LOGIN -> {

                        responseToResult {
                            repo.attemptLogin(loginState.value.email, loginState.value.password)
                        }
                    }
                    AuthAction.SIGN_UP -> {

                        responseToResult {
                            repo.attemptToSignUp(loginState.value.email, loginState.value.password)
                        }
                    }
                }

                when (result) {
                    is Result.Success -> {
                        val id = result.data
                        val username = repo.getUsername(id)
                        val pfp = repo.getPfp(id)

                        user.setUserKeys(
                            id = id,
                            username = username,
                            pfp = pfp
                        )

                        val message = when (type) {
                            AuthAction.LOGIN -> "Successfully logged in"
                            AuthAction.SIGN_UP -> "Successfully signed up"
                        }
                        changeError(false, message)
                    }
                    is Result.Error -> {
                        val errorMessage = when (result.error) {
                            NetworkError.INVALID_CREDENTIAL -> "Invalid credentials"
                            NetworkError.USER_NOT_FOUND -> "User not found"
                            NetworkError.EMAIL_ALREADY_IN_USE -> "Email already in use"
                            NetworkError.FIREBASE_GENERIC -> "An unknown error occurred"
                        }
                        changeError(true, errorMessage)
                    }
                }
            } catch (e: Exception) {
                changeError(true, "Error during operation")
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

    fun registerConfirmed(boolean: Boolean){
        _profileCreationState.update{
            it.copy(
                register = boolean
            )
        }
    }


    fun chooseImage(pfpUri: Uri){
        viewModelScope.launch{
            _profileCreationState.update{
                it.copy(
                    pfpUri = pfpUri
                )
            }
        }

    }


    fun createUser(){
        viewModelScope.launch {
            val id = user.getId().first()
            println("ID: $id")
            val username = profileCreationState.value.username
            val uri = profileCreationState.value.pfpUri

            if (uri != null) {
                repo.creteUser(
                    username = username,
                    id = id,
                    pfpUri = uri
                )
            }
            else{
                println("Failed to get the uri")
            }

            val pfp = repo.getPfp(id)

            if (id != "ERROR") {
                registerConfirmed(true)
                user.setUserKeys(
                    id = id,
                    username = username,
                    pfp = pfp
                )

            }

            else {
                println("not error signing")
                changeError(true, "Error logging in")
            }
        }
    }



    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])

                AuthViewModel(
                    user = UserData(application.dataStore),
                    repo = AuthRepo()
                )
            }
        }
    }
}