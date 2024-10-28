package com.pedro0210.hobbylobby.presentation.screens.Homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.state.HomeScreenState
import com.pedro0210.hobbylobby.presentation.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar
import com.pedro0210.hobbylobby.presentation.viewmodel.home.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    navController: NavController
){
    val state: HomeScreenState by viewModel.state.collectAsStateWithLifecycle()

    Home(
        navController = navController,
        state = state
    )

}


@Composable
fun Home(
    navController: NavController,
    state: HomeScreenState
){
    Scaffold(
        topBar = {
            TopBar(
                navController = navController ,
                homeScreen = true,
            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = state.countries,
                    title =  "Countries",
                    partOfCommunity = true,
                    navController = navController,
                )
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = state.ownCommunities,
                    title = "My Communities",
                    partOfCommunity = true,
                    navController = navController,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HobbyLobbyTheme {
        Home(
            navController = rememberNavController(),
            state = HomeScreenState(
                countries = generateRandomCommunities(10, CommunityType.bigCommunity),
                ownCommunities = generateRandomCommunities(10, CommunityType.smallCommunity)
            )
        )
    }
}


