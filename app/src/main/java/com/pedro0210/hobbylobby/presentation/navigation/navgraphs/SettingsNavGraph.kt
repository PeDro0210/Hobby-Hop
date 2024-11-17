package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pedro0210.hobbylobby.presentation.navigation.AddSocial
import com.pedro0210.hobbylobby.presentation.navigation.CreateBigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CreateSmallCommunity
import com.pedro0210.hobbylobby.presentation.navigation.ModifyProfile
import com.pedro0210.hobbylobby.presentation.navigation.Settings
import com.pedro0210.hobbylobby.presentation.navigation.SettingsGraph
import com.pedro0210.hobbylobby.presentation.screens.Profile.SettingsRoute
import com.pedro0210.hobbylobby.presentation.view.screens.AddingLinksScreenRoute
import com.pedro0210.hobbylobby.presentation.view.screens.ChangingProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.view.screens.CommunitiesCreatorRoute
import com.pedro0210.hobbylobby.presentation.view.screens.SubcommunitiesCreatorRoute
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CreatorViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.SettingsViewModel


fun NavGraphBuilder.settingsGraph(
    navController: NavController,
){

    navigation<SettingsGraph>(
        startDestination =  Settings
    ){

        composable<Settings> {


            val viewModel : SettingsViewModel = viewModel(factory = SettingsViewModel.provideFactory())

            SettingsRoute(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<ModifyProfile>{ navBackStackEntry ->
            val viewModel : ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)

            ChangingProfileScreenRoute(
                viewModel = viewModel,
                navController = navController
            )


        }

        composable<AddSocial>{ navBackStackEntry ->
            val viewModel : ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
            AddingLinksScreenRoute(
                navController = navController,
                viewModel = viewModel

            )
        }



    }

    composable<CreateBigCommunity>{ navBackStackEntry ->
        val viewModelCreator : CreatorViewModel = viewModel(factory = CreatorViewModel.Factory)
        CommunitiesCreatorRoute(
            viewModel = viewModelCreator,
            navController = navController,

        )
    }

    composable<CreateSmallCommunity> { navBackStackEntry ->
        val viewModelCreator : CreatorViewModel = viewModel(factory = CreatorViewModel.Factory)
        SubcommunitiesCreatorRoute(
           navController = navController,
              viewModel = viewModelCreator
       )

    }
}
