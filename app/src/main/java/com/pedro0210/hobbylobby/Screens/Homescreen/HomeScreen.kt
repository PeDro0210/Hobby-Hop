package com.pedro0210.hobbylobby.Screens.Homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.presentation.model.ButtonType
import com.pedro0210.hobbylobby.presentation.state.HomeScreenState
import com.pedro0210.hobbylobby.presentation.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    state: HomeScreenState
){
    Scaffold(
        topBar = {
            TopBar(
                navController = navController ,
                homeScreen = false,
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
                    buttonType = ButtonType.bigCommunity
                )
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = state.ownCommunities,
                    title = "My Communities",
                    partOfCommunity = true,
                    navController = navController,
                    buttonType = ButtonType.smallCommunity
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
                countries = generateRandomCommunities(10),
                ownCommunities = generateRandomCommunities(10)
            )
        )
    }
}


