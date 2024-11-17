package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.User

data class UserState (
    val user: User,
    override var isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val hasError: Boolean = false,

):IState