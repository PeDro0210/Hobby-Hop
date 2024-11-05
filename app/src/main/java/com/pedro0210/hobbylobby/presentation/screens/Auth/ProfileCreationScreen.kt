package com.pedro0210.hobbylobby.presentation.screens.Auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateFromLogin
import com.pedro0210.hobbylobby.presentation.screens.Profile.ProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import com.pedro0210.hobbylobby.presentation.viewmodel.login.AuthViewModel
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun ProfileCreationRoute(
    viewModel: AuthViewModel,
    navController: NavController
){
    val state: ProfileCreationState by viewModel.profileCreationState.collectAsStateWithLifecycle()

    ProfileCreationScreen(
        navController = navController,
        state = state,
        onUsernameChange = { viewModel.changeUsername(it) },
        onRegisterClick = { /*TODO*/ }
    )
}


@Composable
fun ProfileCreationScreen(
    navController: NavController,
    state: ProfileCreationState,
    onUsernameChange: (String) -> Unit,
    onRegisterClick: () -> Unit
){

    Scaffold(
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "What's your name? Hobbyist",
                    style = MaterialTheme.typography.titleLarge,
                )


                OutlinedTextField(
                    value = state.username,
                    onValueChange = onUsernameChange,
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                )


                Button(
                    onClick = {
                        onRegisterClick()
                        navController.navigateFromLogin(Home)
                    },
                ){
                    Text("Join Us")
                }

            }
        }
    )


}


@Preview(showBackground = true)
@Composable
fun ProfileCreationPreview() {
    HobbyLobbyTheme {
        val profileCreationViewModel : AuthViewModel = viewModel(
            factory = AuthViewModel.provideFactory()
        )

        ProfileCreationRoute(
            viewModel = profileCreationViewModel ,
            navController = rememberNavController(),
        )


    }
}
