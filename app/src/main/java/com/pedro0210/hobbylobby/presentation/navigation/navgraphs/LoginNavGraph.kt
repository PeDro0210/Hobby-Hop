package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pedro0210.hobbylobby.presentation.navigation.Login
import com.pedro0210.hobbylobby.presentation.navigation.LoginGraph
import com.pedro0210.hobbylobby.presentation.screens.Login.LoginRoute
import com.pedro0210.hobbylobby.presentation.viewmodel.login.LoginViewModel

fun NavGraphBuilder.loginGraph(
    navController: NavController,
){
    navigation<LoginGraph>(
        startDestination = Login
    ){
        composable<Login> {
            val loginViewModel : LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory()
            )
            LoginRoute(
                viewModel = loginViewModel,
                navController = navController,
            )

        }
    }
}