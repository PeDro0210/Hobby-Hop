package com.pedro0210.hobbylobby.presentation.screens.Profile

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.state.ProfileState
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel

@Composable
fun SettingsRoute(
    viewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
){
    val state: ProfileState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onEvent(ProfileEvent.onLoadUser(1))
    }

    SettingsScreen(
        userName = state.user?.name ?: "Nombre",
        onRoomsClick = {},
        onCreateCommunityClick = {},
        onEditProfileClick = {},
        onSignOutClick = {}
    )
}

@Composable
fun SettingsScreen(
    userName: String,
    onRoomsClick: () -> Unit = {},
    onCreateCommunityClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Imagen y nombre de usuario
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.avatar), // Placeholder image
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = userName, style = MaterialTheme.typography.headlineLarge)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones de navegación
        Button(
            onClick = onRoomsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Rooms")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCreateCommunityClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear Comunidad")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEditProfileClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Modificar Perfil")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de cerrar sesión
        Button(
            onClick = onSignOutClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Sign Out")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        userName = "Pedro",
        onRoomsClick = {},
        onCreateCommunityClick = {},
        onEditProfileClick = {},
        onSignOutClick = {}
    )
}