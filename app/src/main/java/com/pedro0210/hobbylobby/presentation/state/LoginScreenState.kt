package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.SignUp

data class LoginScreenState(
    // States for sending the view model
    var email: String = "",
    var password: String = "",

    // Just for the screen
    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isLogged: Boolean = false,
    var hasError: Boolean = true,

    var buttonText: String = "Join Us",
    var navDestination: AuthDestionation = SignUp,
    var boxChecked: Boolean = false

)