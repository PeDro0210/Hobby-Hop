package com.pedro0210.hobbylobby.presentation.model

import android.net.Uri

data class Profile (
    val id: String,
    val name: String,
    val description: String,
    val image: Uri?,
    val imageUrl: String,
    val socialMedia: List<SocialMediaCreation>,
)