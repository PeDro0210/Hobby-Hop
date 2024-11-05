package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.navigation.Home

data class LoginScreenState(
    // States for sending the view model
    var email: String = "",
    var password: String = "",

    // Just for the screen
    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isLogged: Boolean = false,

    var buttonText: String = "Login",
    var navDestination: AuthDestionation = Home,
    var boxChecked: Boolean = false

)