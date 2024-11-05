package com.pedro0210.hobbylobby.presentation.navigation.routers

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity


fun NavController.navigateFromLogin(
    destination: AuthDestionation,
    navOptions: NavOptions? = null
){
    if (destination == Home) popBackStack()

    this.navigate(destination, navOptions)
}



fun NavController.navigateToBigCommunities(
    destination: BigCommunity,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavController.navigateToSmallCommunities(
    destination: SmallCommunity,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavController.navigateToRooms(
    destination: Rooms,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavController.navigateToProfile(
    destination: Profile,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}