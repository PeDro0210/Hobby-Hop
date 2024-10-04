package com.pedro0210.hobbylobby.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ComunitiesState(
    val id:String,
    val image: String,
    val title: String,
    val description: String,
    val partOfCommunity: Boolean,
    val bigCommunity: Boolean = true,

    var searchText: String = "",
)