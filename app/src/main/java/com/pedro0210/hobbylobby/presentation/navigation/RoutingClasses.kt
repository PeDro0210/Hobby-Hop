package com.pedro0210.hobbylobby.presentation.navigation

import kotlinx.serialization.Serializable


//Login Graph
@Serializable object LoginGraph

interface AuthDestionation

@Serializable object Login

@Serializable object SignUp:AuthDestionation

//Communities Graph
@Serializable object CommunitiesGraph

@Serializable object Home:AuthDestionation



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
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val partOfCommunity: Boolean
)


@Serializable object AdminGraph

@Serializable object AdminCommunity

@Serializable
data class AdminRoom(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
)

@Serializable
data class Requests(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
)


@Serializable
data class Profile(
    val id: String,
    val title: String,
    val image: String,
)

@Serializable object SettingsGraph

@Serializable object Settings

@Serializable object ModifyProfile

@Serializable object AddSocial

@Serializable object CreateBigCommunity

@Serializable object CreateSmallCommunity
