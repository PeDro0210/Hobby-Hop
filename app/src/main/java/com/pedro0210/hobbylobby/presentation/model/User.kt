package com.pedro0210.hobbylobby.presentation.model

data class User (
    val id: String, //TODO: change UIID to string
    val name: String,
    val description: String,
    val image: String,
    val socialMedia: List<SocialMedia>,
)