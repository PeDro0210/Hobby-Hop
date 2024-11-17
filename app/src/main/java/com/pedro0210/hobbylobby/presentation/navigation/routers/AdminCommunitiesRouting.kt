package com.pedro0210.hobbylobby.presentation.navigation.routers

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pedro0210.hobbylobby.presentation.navigation.AdminCommunity
import com.pedro0210.hobbylobby.presentation.navigation.AdminRoom
import com.pedro0210.hobbylobby.presentation.navigation.Requests


fun NavController.navigateToAdminCommunities(
    navOptions: NavOptions? = null
){
    this.navigate(AdminCommunity, navOptions)
}

fun NavController.navigateToAdminRooms(
    destination: AdminRoom,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavController.navigateToRequests(
    destination: Requests,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}
