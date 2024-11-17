package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.pedro0210.hobbylobby.data.repository.CommunitiesRepo
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.AdminCommunity
import com.pedro0210.hobbylobby.presentation.navigation.AdminGraph
import com.pedro0210.hobbylobby.presentation.navigation.AdminRoom
import com.pedro0210.hobbylobby.presentation.navigation.Requests
import com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff.AceptacionesScreenRoute
import com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff.AdminCommunitiesRoute
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.AdminViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.AceptacionesViewModel

fun NavGraphBuilder.adminGraph(
    navController: NavController,
) {
    navigation<AdminGraph>(
        startDestination = AdminCommunity
    ) {

        val communityRepo = CommunitiesRepo()
        composable<AdminCommunity>{
            val adminCommunitiesViewModel: AdminViewModel = viewModel(
                factory = AdminViewModel.provideFactory(
                    communityType = CommunityType.communities,
                    repo = communityRepo
                )
            )


            AdminCommunitiesRoute(
                viewModel = adminCommunitiesViewModel,
                navController = navController
            )

        }

        composable<AdminRoom>{ navBackStackEntry ->

            val communityId = navBackStackEntry.toRoute<AdminRoom>()

            val adminCommunitiesViewModel: AdminViewModel = viewModel(
                factory = AdminViewModel.provideFactory(
                    communityType = CommunityType.rooms,
                    repo = communityRepo,
                    id = communityId.id
                )
            )


            AdminCommunitiesRoute(
                viewModel = adminCommunitiesViewModel,
                navController = navController
            )

        }


        composable<Requests>{navBackStackEntry ->
            val requests = navBackStackEntry.toRoute<Requests>()

            val requestsViewModel: AceptacionesViewModel = viewModel(
                factory = AceptacionesViewModel.provideFactory(
                    id = requests.id,
                    name = requests.title,
                    image = requests.image,
                    description = requests.description
                )
            )


            AceptacionesScreenRoute(
                viewModel = requestsViewModel,
                navController = navController
            )

        }
    }
}