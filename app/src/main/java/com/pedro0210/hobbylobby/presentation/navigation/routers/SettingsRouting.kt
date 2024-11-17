package com.pedro0210.hobbylobby.presentation.navigation.routers

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pedro0210.hobbylobby.presentation.navigation.AddSocial
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CreateBigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CreateSmallCommunity
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.ModifyProfile
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.Settings
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity

fun NavController.navigateToSettings(
    navOptions: NavOptions? = null
){
    this.navigate(Settings, navOptions)
}

fun NavController.navigateToModiyProfile(
    navOptions: NavOptions? = null
){
    this.navigate(ModifyProfile, navOptions)
}

fun NavController.navigateToAddSocial(
    navOptions: NavOptions? = null
){
    this.navigate(AddSocial, navOptions)
}

fun NavController.navigateToCreateBigCommunity(
    navOptions: NavOptions? = null
){
    this.navigate(CreateBigCommunity, navOptions)
}

fun NavController.navigateToCreateSmallCommunities(
    navOptions: NavOptions? = null
){
    this.navigate(CreateSmallCommunity, navOptions)
}
