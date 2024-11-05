package com.pedro0210.hobbylobby.presentation.navigation.navgraphs

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pedro0210.hobbylobby.presentation.navigation.Login
import com.pedro0210.hobbylobby.presentation.navigation.LoginGraph
import com.pedro0210.hobbylobby.presentation.navigation.SignUp
import com.pedro0210.hobbylobby.presentation.screens.Auth.LoginRoute
import com.pedro0210.hobbylobby.presentation.screens.Auth.ProfileCreationRoute
import com.pedro0210.hobbylobby.presentation.screens.Auth.ProfileCreationScreen
import com.pedro0210.hobbylobby.presentation.screens.Profile.ProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.viewmodel.login.AuthViewModel

fun NavGraphBuilder.loginGraph(
    navController: NavController,
){
    navigation<LoginGraph>(
        startDestination = Login
    ){
        composable<Login> {
            val loginViewModel : AuthViewModel = viewModel(
                factory = AuthViewModel.provideFactory()
            )
            LoginRoute(
                viewModel = loginViewModel,
                navController = navController,
            )

        }

        composable<SignUp> {
            val profileCreationViewModel : AuthViewModel = viewModel(
                factory = AuthViewModel.provideFactory()
            )

            ProfileCreationRoute(
                viewModel = profileCreationViewModel,
                navController = navController
            )
        }
    }
}