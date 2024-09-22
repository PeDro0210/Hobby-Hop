package com.pedro0210.hobbylobby.ui.view.screens

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
import com.pedro0210.hobbylobby.controller.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.ui.view.widgets.CommunitiesColumns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
){
    Scaffold(
        //TODO: Add the top bar with search field
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = generateRandomCommunities(10),
                    title =  "Countries"
                )
                CommunitiesColumns(
                    modifier = Modifier.weight(0.5f),
                    communities = generateRandomCommunities(10),
                    title = "My Communities"
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


