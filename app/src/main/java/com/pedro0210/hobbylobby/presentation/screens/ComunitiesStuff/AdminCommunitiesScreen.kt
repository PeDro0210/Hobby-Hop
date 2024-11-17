package com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.state.AdminCommunitiesState
import com.pedro0210.hobbylobby.presentation.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.AdminViewModel
import com.pedro0210.hobbylobby.presentation.widgets.communities.util.routingSettingsCommunities

@Composable
fun AdminCommunitiesRoute(
    viewModel: AdminViewModel,
    navController: NavController,
){
    val state: AdminCommunitiesState by viewModel.state.collectAsStateWithLifecycle()


    AdminCommunitiesScreen(
        navController = navController,
        state = state,
        onClickNavigation = {image, name, description, id, partOfCommunity, navController, type ->
            routingSettingsCommunities(
                image = image,
                name = name,
                description = description,
                id = id,
                partOfCommunity = partOfCommunity,
                navController = navController,
                type = type
            )
        }
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun AdminCommunitiesScreen(
    navController: NavController,
    state: AdminCommunitiesState,
    onClickNavigation: (
        image: String,
        name: String,
        description: String,
        id: String,
        partOfCommunity: Boolean,
        navController: NavController,
        type: CommunityType
    ) -> Unit
){

    Scaffold (
        topBar = {
            TopBar(
                navController = navController ,
                homeScreen = false,
                settingsScreen = true,
            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CommunitiesColumns(
                    modifier = Modifier.weight(0.65f),
                    communities = state.communities,
                    title = if (state.communities.isEmpty())
                        "There's no communities"
                    else if (state.type == CommunityType.communities)
                        "Your Communities"
                    else
                        "Rooms",
                    partOfCommunity = true,
                    navController = navController,
                    onClickNavigation = onClickNavigation
                )


            }
        }
    )

}