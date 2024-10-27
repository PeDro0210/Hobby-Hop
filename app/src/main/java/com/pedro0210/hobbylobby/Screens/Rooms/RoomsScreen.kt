package com.pedro0210.hobbylobby.Screens.Rooms


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.Screens.Rooms.Viewmodel.RoomMember
import com.pedro0210.hobbylobby.Screens.Rooms.Viewmodel.RoomsViewModel

@Composable
fun RoomScreenRoute(
    viewModel: RoomsViewModel = viewModel()
) {
    RoomScreen()
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen() {
    val members = remember {
        listOf(
            RoomMember("Juan", "Conectado", R.drawable.avatar),
            RoomMember("Abby", "18 min", R.drawable.avatar),
            RoomMember("Oscar", "Me gusta Pokémon", R.drawable.avatar),
            RoomMember("Name", "18 min", R.drawable.avatar),
            RoomMember("Name", "18 min", R.drawable.avatar),
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Gaming") },

            )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Comunidad para personas que les gustan los juegos.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Acción de unirse al room */ },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.End)
        ) {
            Text("Unirse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Miembros del room:",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            members.forEach { member ->
                MemberRow(member = member)
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun MemberRow(member: RoomMember) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
    RoomScreen()
}
