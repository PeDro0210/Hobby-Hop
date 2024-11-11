package com.pedro0210.hobbylobby.presentation.screens.Rooms


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import com.pedro0210.hobbylobby.presentation.navigation.Profile
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToProfile
import com.pedro0210.hobbylobby.presentation.state.RoomScreenState
import com.pedro0210.hobbylobby.presentation.viewmodel.rooms.RoomsViewModel

@Composable
fun RoomRoute(
    viewModel: RoomsViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    RoomScreen(
        navController = navController,
        uiState = uiState,
        onJoinClick = { viewModel.joinRoom() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
    navController: NavController,
    uiState: RoomScreenState,
    onJoinClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = uiState.roomName) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = uiState.roomDescription,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onJoinClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.End)
        ) {
            Text(if (uiState.isJoined) "Unido" else "Unirse")
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
                        navController.navigate("profile/${member.name}")
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = member.avatarResource),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = member.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = member.status,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Text(
            text = "Conectado",
            color = Color.Green,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoomScreen() {
    RoomScreen(
        navController = rememberNavController(),
        uiState = RoomScreenState(
            roomName = "Room Name",
            roomDescription = "Room Description",
            users = listOf(
                RoomMember("Juan", "Conectado", R.drawable.avatar),
                RoomMember("Abby", "18 min", R.drawable.avatar),
                RoomMember("Oscar", "Me gusta Pokémon", R.drawable.avatar),
                RoomMember("Name", "18 min", R.drawable.avatar),
                RoomMember("Name", "18 min", R.drawable.avatar)
            )
        ),
        onJoinClick = {}
    )
}