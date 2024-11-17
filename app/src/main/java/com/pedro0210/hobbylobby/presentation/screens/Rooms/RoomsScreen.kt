package com.pedro0210.hobbylobby.presentation.screens.Rooms


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToProfile
import com.pedro0210.hobbylobby.presentation.state.RoomScreenState
import com.pedro0210.hobbylobby.presentation.util.NoRippleTheme
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.RoomsViewModel

@Composable
fun RoomRoute(
    viewModel: RoomsViewModel,
    navController: NavController,
    userId: String
) {
    val uiState by viewModel.uiState.collectAsState()


    if (uiState.isRequestPending) {
        LaunchedEffect(userId) {
            viewModel.checkRoomAcceptance(userId)
        }
    }

    RoomScreen(
        uiState = uiState,
        onJoinClick = {
            if (uiState.isJoined) {
                viewModel.leaveRoom(userId)
            } else {
                viewModel.attemptJoinRoom(userId)
            }
        },
        onNavigateToProfile = { id, title, image ->
            navController.navigateToProfile(
                Profile(
                    id = id,
                    title = title,
                    image = image
                )
            )
        },
        navController = navController
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
    navController: NavController,
    uiState: RoomScreenState,
    onJoinClick: () -> Unit,
    onNavigateToProfile: (String, String, String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            navController = navController,
            homeScreen = false,
            settingsScreen = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            AsyncImage(
                modifier = Modifier
                    .weight(0.3f)
                    .size(128.dp)
                    .clip(CircleShape),
                model = uiState.roomImage,
                contentDescription = "Room Image"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.7f)
            ) {
                Text(
                    text = uiState.roomDescription,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        when {
            uiState.isRequestPending -> {
                Text(
                    text = "Esperando aprobación...",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            uiState.isJoined -> {
                Button(
                    onClick = onJoinClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End)
                ) {
                    Text("Salir")
                }
            }
            else -> {
                Button(
                    onClick = onJoinClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End)
                ) {
                    Text("Unirse")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Miembros del room:",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            uiState.users.forEach { member ->
                MemberRow(
                    member = member,
                    onItemClick = {
                        onNavigateToProfile(
                            member.id,
                            member.name,
                            member.pfp
                        )
                    }
                )
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}


@Composable
fun MemberRow(
    member: RoomMember,
    onItemClick: () -> Unit = {}
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            onClick =  onItemClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = member.pfp,
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.secondary),
                    clipToBounds = true

                )



                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        color = Black,
                        text = member.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                }

            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewRoomScreen() {
//    RoomScreen(
//        uiState = RoomScreenState(
//            roomName = "Room Name",
//            roomDescription = "Room Description",
//            users = listOf(
//
//            )
//        ),
//        onJoinClick = {},
//        onNavigateToProfile = {}
//    )
//}