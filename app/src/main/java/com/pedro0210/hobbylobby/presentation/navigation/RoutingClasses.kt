package com.pedro0210.hobbylobby.presentation.navigation

import kotlinx.serialization.Serializable


//Login Graph
@Serializable object LoginGraph

@Serializable object Login

//Communities Graph
@Serializable object CommunitiesGraph

@Serializable object Home

@Serializable
data class BigCommunity(
    val id: String
)

@Serializable
data class SmallCommunity(
    val id: String
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
