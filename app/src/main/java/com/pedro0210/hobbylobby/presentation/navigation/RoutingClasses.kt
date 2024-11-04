package com.pedro0210.hobbylobby.presentation.navigation

import kotlinx.serialization.Serializable


//Login Graph
@Serializable object LoginGraph


interface Auth

@Serializable object Login:Auth

@Serializable object SignUp

//Communities Graph
@Serializable object CommunitiesGraph

@Serializable object Home:Auth

@Serializable
data class BigCommunity(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val partOfCommunity: Boolean
)

@Serializable
data class SmallCommunity(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val partOfCommunity: Boolean
)

@Serializable
data class Rooms(
    val id: String
)

@Serializable
data class Profile(
    val id: String
)

@Serializable object SettingsGraph

@Serializable object Settings

@Serializable object ModifyProfile

@Serializable object AddSocial

@Serializable object CreateBigCommunity

@Serializable object CreateSmallCommunity
