package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.User

data class ProfileState (
    val user: User? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val hasError: Boolean = false,

)