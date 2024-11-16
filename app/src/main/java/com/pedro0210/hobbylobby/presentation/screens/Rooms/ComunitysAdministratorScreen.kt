package com.pedro0210.hobbylobby.presentation.screens.Rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.AceptacionesViewModel

@Composable
fun AceptacionesScreenRoute(
    navController: NavController,
    viewModel: AceptacionesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AceptacionesViewModel.provideFactory())
) {
    val uiState by viewModel.uiState.collectAsState()

    AceptacionesScreen(
        navController = navController,
        communityName = uiState.roomName,
        communityDescription = uiState.roomDescription,
        requests = uiState.aceptaciones,
        onAcceptClick = { userName -> viewModel.acceptRequest(userName) },
        onRejectClick = { userName -> viewModel.rejectRequest(userName) },
        onBackClick = { navController.popBackStack() },
        onEditClick = { /* Acción de edición */ }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AceptacionesScreen(
    navController: NavController,
    communityName: String,
    communityDescription: String,
    requests: List<String>,
    onEditClick: () -> Unit = {},
    onAcceptClick: (String) -> Unit = {},
    onRejectClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Aceptaciones") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { /* Opciones adicionales */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
            }
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.street_fighter_logo), // Placeholder image
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = communityName, style = MaterialTheme.typography.headlineLarge)
                    Text(text = communityDescription)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Solicitudes", style = MaterialTheme.typography.bodyLarge)

            LazyColumn {
                items(requests) { request ->
                    RequestItem(
                        userName = request,
                        onAcceptClick = { onAcceptClick(request) },
                        onRejectClick = { onRejectClick(request) }
                    )
                }
            }
        }
    }
}

@Composable
fun RequestItem(userName: String, onAcceptClick: () -> Unit, onRejectClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = userName, modifier = Modifier.weight(1f))
        IconButton(onClick = onAcceptClick) {
            Icon(Icons.Default.Check, contentDescription = "Accept", tint = Color.Green)
        }
        IconButton(onClick = onRejectClick) {
            Icon(Icons.Default.Close, contentDescription = "Reject", tint = Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAceptacionesScreen() {
    AceptacionesScreen(
        communityName = "Street Fighter 3 Guatemala",
        communityDescription = "Comunidad de juego de peleas",
        requests = listOf("Juan", "Anna", "Marcos", "Luis", "Carlos"),
        onAcceptClick = {},
        onRejectClick = {},
        onBackClick = {},
        onEditClick = {},
        navController = rememberNavController()
    )
}
