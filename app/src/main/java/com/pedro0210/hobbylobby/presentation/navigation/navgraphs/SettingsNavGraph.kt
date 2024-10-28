package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.AddSocial
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CommunitiesGraph
import com.pedro0210.hobbylobby.presentation.navigation.CreateBigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CreateSmallCommunity
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.ModifyProfile
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.Settings
import com.pedro0210.hobbylobby.presentation.navigation.SettingsGraph
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity
import com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff.CommunitiesRoute
import com.pedro0210.hobbylobby.presentation.screens.Homescreen.HomeRoute
import com.pedro0210.hobbylobby.presentation.screens.Profile.ProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.screens.Profile.SettingsRoute
import com.pedro0210.hobbylobby.presentation.screens.Rooms.RoomRoute
import com.pedro0210.hobbylobby.presentation.view.screens.AddingLinksScreenRoute
import com.pedro0210.hobbylobby.presentation.view.screens.ChangingProfileScreen
import com.pedro0210.hobbylobby.presentation.view.screens.ChangingProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.view.screens.CommunitiesCreatorRoute
import com.pedro0210.hobbylobby.presentation.view.screens.SubcommunitiesCreatorRoute
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CommunitiesViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.home.HomeViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.UserViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.RoomsViewModel

fun NavGraphBuilder.settingsGraph(
    navController: NavController,
){
    navigation<SettingsGraph>(
        startDestination =  Settings
    ){
        composable<Settings> {

            val viewModel : ProfileViewModel = viewModel(
                factory = ProfileViewModel.provideFactory()
            )

            SettingsRoute(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<ModifyProfile>{ navBackStackEntry ->
            val viewModel : ProfileViewModel = viewModel(
                factory = ProfileViewModel.provideFactory()
            )

            ChangingProfileScreenRoute(
                viewModel = viewModel,
                navController = navController
            )


        }

        composable<AddSocial>{ navBackStackEntry ->

            AddingLinksScreenRoute(
                navController = navController
            )
        }


    }

    composable<CreateBigCommunity>{ navBackStackEntry ->
        CommunitiesCreatorRoute(
            navController = navController
        )
    }

    composable<CreateSmallCommunity> { navBackStackEntry ->

       SubcommunitiesCreatorRoute(
           navController = navController
       )

    }
}
