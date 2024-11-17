package com.pedro0210.hobbylobby.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.adminGraph
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.communitiesGraph
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.loginGraph
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.settingsGraph
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
){
    HobbyLobbyTheme {
        NavHost(
            navController = navHostController,
            startDestination = LoginGraph
        ){

            // All the navigation graphs
            loginGraph(navHostController)
            communitiesGraph(navHostController)
            settingsGraph(navHostController)
            adminGraph(navHostController)
        }
    }
}