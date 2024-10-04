package com.pedro0210.hobbylobby.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.controller.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.ui.view.widgets.SearchTopBar

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,

){
    //TODO: make the view Model do the take care of the searchText

    Scaffold(
        topBar = {
            SearchTopBar(
                navController = navController ,
                searchText = "",
                homeScreen = false,
                onValueChange = {} //TODO: add the view model
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
                    communities = generateRandomCommunities(10),
                    title =  "Countries",
                    partOfCommunity = true
                )
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = generateRandomCommunities(10),
                    title = "My Communities",
                    partOfCommunity = true
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HobbyLobbyTheme {
        Home(navController = rememberNavController())
    }
}


