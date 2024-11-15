package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri
import com.pedro0210.hobbylobby.presentation.model.Community


data class CreatorScreenState(
    val id:String = "",
    val image: Uri? = null,
    val title: String = "",
    val description: String = "",
    val rooms: List<Community> = emptyList(),
    val hasError: Boolean = false,
    val isLoading: Boolean = false

)