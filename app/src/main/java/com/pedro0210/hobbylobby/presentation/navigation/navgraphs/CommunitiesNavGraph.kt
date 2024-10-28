package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.CommunitiesGraph
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.Login
import com.pedro0210.hobbylobby.presentation.navigation.LoginGraph
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity
import com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff.CommunitiesRoute
import com.pedro0210.hobbylobby.presentation.screens.Homescreen.HomeRoute
import com.pedro0210.hobbylobby.presentation.screens.Login.LoginRoute
import com.pedro0210.hobbylobby.presentation.screens.Profile.ProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.screens.Rooms.RoomRoute
import com.pedro0210.hobbylobby.presentation.state.ComunitiesScreenState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CommunitiesViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.home.HomeViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.login.LoginViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.UserViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.RoomsViewModel

fun NavGraphBuilder.communitiesGraph(
    navController: NavController,
){
    navigation<CommunitiesGraph>(
        startDestination =  Home
    ){
        composable<Home> {

            val homeViewModel : HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory()
            )

            HomeRoute(
                navController = navController,
                viewModel = homeViewModel
            )
        }

        composable<BigCommunity>{ navBackStackEntry ->

           val communityId: BigCommunity = navBackStackEntry.toRoute()

           val bigCommunityViewModel : CommunitiesViewModel = viewModel(
               factory = CommunitiesViewModel.provideFactory(
                    id = communityId.id,
                    communityType = CommunityType.bigCommunity
               )
           )

            CommunitiesRoute(
                viewModel = bigCommunityViewModel,
                navController = navController
            )
        }

        composable<SmallCommunity>{ navBackStackEntry ->

           val communityId: BigCommunity = navBackStackEntry.toRoute()

           val smallCommunityViewModel : CommunitiesViewModel = viewModel(
               factory = CommunitiesViewModel.provideFactory(
                    id = communityId.id,
                    communityType = CommunityType.smallCommunity
               )
           )

            CommunitiesRoute(
                viewModel = smallCommunityViewModel,
                navController = navController
            )
        }


    }

    composable<Rooms>{ navBackStackEntry ->
        val roomId: Rooms = navBackStackEntry.toRoute()

        val roomViewModel : RoomsViewModel = viewModel(
            factory = RoomsViewModel.provideFactory(
                id = roomId.id
            )
        )

        RoomRoute(
            viewModel = roomViewModel,
            navController = navController
        )
    }

    composable<Profile> {navBackStackEntry ->

        val profileId: Profile = navBackStackEntry.toRoute()

        val profileViewModel : UserViewModel = viewModel(
            factory = UserViewModel.provideFactory(
                id = profileId.id
            )
        )

        ProfileScreenRoute(
            viewModel = profileViewModel,
            navController = navController
        )
    }
}
