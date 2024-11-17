package com.pedro0210.hobbylobby.presentation.screens.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.navigation.Login
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToAdminCommunities
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToCreateBigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToModiyProfile
import com.pedro0210.hobbylobby.presentation.state.SettingsState
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.SettingsViewModel

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel,
    navController: NavController
){
    val state: SettingsState by viewModel.settingsState.collectAsStateWithLifecycle()

    SettingsScreen(
        navController = navController,
        userName = state.username,
        pfp = state.pfp,
        onCommunitiesClick = {navController.navigateToAdminCommunities()}, //change this
        onCreateCommunityClick = {navController.navigateToCreateBigCommunity()},
        onEditProfileClick = {navController.navigateToModiyProfile()},
        onSignOutClick = {
            viewModel.onLogoutClick()
            navController.navigate(Login)
        }
    )


}

@Composable
fun SettingsScreen(
    navController: NavController,
    userName: String,
    pfp: String,
    onCommunitiesClick: () -> Unit = {},
    onCreateCommunityClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    Scaffold (
        topBar = {
            TopBar(
                navController = navController ,
                homeScreen = false,
                settingsScreen = false
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Imagen y nombre de usuario
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp,0.dp)) {
                    AsyncImage(
                        model = pfp,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = userName, style = MaterialTheme.typography.headlineLarge)


                    Spacer(modifier = Modifier.height(32.dp))

                    // Botones de navegación
                    Button(
                        onClick = onCommunitiesClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "My Communities")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onCreateCommunityClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Create Communities")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onEditProfileClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Modify Profile")
                    }
                }
                //Spacer(modifier = Modifier.height(32.dp))
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp,0.dp)){
                    // Botón de cerrar sesión
                    Button(
                        onClick = onSignOutClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "Sign Out")
                    }
                }


            }
        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    SettingsScreen(
//        userName = "Pedro",
//        onCommunitiesClick = {},
//        onCreateCommunityClick = {},
//        onEditProfileClick = {},
//        onSignOutClick = {},
//        pfp = "https://www.google.com/"
//    )
//}