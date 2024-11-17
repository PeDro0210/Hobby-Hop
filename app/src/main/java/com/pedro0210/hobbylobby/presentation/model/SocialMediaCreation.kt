package com.pedro0210.hobbylobby.presentation.model

import android.net.Uri

data class SocialMediaCreation (
    val name: String,
    val image: Uri?,
    val imageUrl: String,
    val url: String,
    val new: Boolean = false
)