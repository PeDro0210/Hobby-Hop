package com.pedro0210.hobbylobby.presentation.state

data class AceptacionesScreenState (
    val aceptaciones: List<String> = emptyList(),
    val roomName: String = "",
    val roomDescription: String = ""
){
}