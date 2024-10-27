package com.pedro0210.hobbylobby.presentation.model

data class User (
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val socialMedia: List<SocialMedia>
)