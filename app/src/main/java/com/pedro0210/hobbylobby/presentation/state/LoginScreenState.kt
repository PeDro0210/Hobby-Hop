package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.navigation.Auth
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.util.LoginNavEnum

data class LoginScreenState(
    // States for sending the view model
    var email: String = "",
    var password: String = "",

    // Just for the screen
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,

    var buttonText: String = "Login",
    var navDestination: Auth = Home,
    var boxChecked: Boolean = false
)