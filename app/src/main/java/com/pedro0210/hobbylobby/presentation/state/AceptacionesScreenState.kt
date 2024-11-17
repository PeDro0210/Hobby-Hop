package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.RoomMember

data class AcceptanceScreenState (
    val requests: List<RoomMember>,
    val name: String,
    val description: String,
    val image: String,
    override var isLoading: Boolean = true
):IState