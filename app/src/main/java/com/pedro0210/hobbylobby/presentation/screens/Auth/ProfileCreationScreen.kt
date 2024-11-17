package com.pedro0210.hobbylobby.presentation.screens.Auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateFromLogin
import com.pedro0210.hobbylobby.presentation.screens.Profile.ProfileScreenRoute
import com.pedro0210.hobbylobby.presentation.state.CreationState
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import com.pedro0210.hobbylobby.presentation.util.NoRippleTheme
import com.pedro0210.hobbylobby.presentation.viewmodel.login.AuthViewModel
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun ProfileCreationRoute(
    viewModel: AuthViewModel,
    navController: NavController
){
    val state: CreationState by viewModel.profileCreationState.collectAsStateWithLifecycle()


    LaunchedEffect(state) {
        println("STATE: $state")
        if (state.register) {
            navController.navigateFromLogin(Home)
        }
    }
    ProfileCreationScreen(
        navController = navController,
        state = state,
        onUsernameChange = { viewModel.changeUsername(it) },
        onImageChange = { viewModel.chooseImage(it)},
        onRegisterClick = { viewModel.createUser()}
    )
}


@Composable
fun ProfileCreationScreen(
    navController: NavController,
    state: CreationState,
    onUsernameChange: (String) -> Unit,
    onImageChange: (Uri) -> Unit,
    onRegisterClick: () -> Unit
){

    // fuck good design, I just want something that works
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageChange(uri)
        }
    }


    //TODO: if someone want's to improve this screen, do it pls
    Scaffold(
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                    Button(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        if (state.pfpUri == null) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Profile Picture"
                                )
                            }
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = state.pfpUri)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                        }).build()
                                ),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(128.dp)
                                    .clip(CircleShape)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop

                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "What's your name?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                )


                OutlinedTextField(
                    value = state.username,
                    onValueChange = onUsernameChange,
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth().padding(16.dp , 0.dp),
                )


                Button(
                    onClick = {
                        onRegisterClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ){
                    Text("Join")
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
