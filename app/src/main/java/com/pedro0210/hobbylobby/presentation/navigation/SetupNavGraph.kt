package com.pedro0210.hobbylobby.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.communitiesGraph
import com.pedro0210.hobbylobby.presentation.navigation.navgraphs.loginGraph

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = LoginGraph
    ){
        loginGraph(navHostController)
        communitiesGraph(navHostController)
    }
}