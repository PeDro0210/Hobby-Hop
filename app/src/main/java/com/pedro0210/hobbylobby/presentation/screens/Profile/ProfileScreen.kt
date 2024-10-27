package com.pedro0210.hobbylobby.presentation.screens.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
    description: String,
    ondescriptionChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    onBackClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onPictureChange: () -> Unit = {},
    onAddClick: () -> Unit = {}

) {
    ElementsScreen(
        description = description,
        ondescriptionChange = ondescriptionChange,
        name = name,
        onNameChange = onNameChange,
        onBackClick = onBackClick,
        onClearClick = onClearClick,
        onPictureChange = onPictureChange,
        onAddClick = onAddClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementsScreen(modifier: Modifier = Modifier,
                   description: String,
                   ondescriptionChange: (String) -> Unit,
                   name: String,
                   onNameChange: (String) -> Unit,
                   onBackClick: () -> Unit = {},
                   onClearClick: () -> Unit = {},
                   onPictureChange: () -> Unit = {},
                   onAddClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(title = {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Oscar's profile")
            }
        },
            actions = {
                IconButton(onClick = { /* Aquí agregar lógica si se desea */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
            })

        // Contenido del perfil
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)) {

            // Avatar y nombre
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {

                Box(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(150.dp)
                    .clip(CircleShape),
                    contentAlignment = androidx.compose.ui.Alignment.Center) {
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.weight(1f))
            }

            // Descripción
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = description, style = MaterialTheme.typography.bodyLarge)

            // Sección de redes sociales
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Oscar's social media:", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    SocialSquare("SNAPCHAT", R.drawable.snapchat_logo)
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    SocialSquare("INSTAGRAM", R.drawable.instagram_logo)
                }
            }
        }
    }
}

@Composable
fun SocialSquare(name: String, iconResId: Int) {
    Row (modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ){
        Row(modifier = Modifier.fillMaxWidth(0.65f),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .fillMaxWidth(0.35f),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {

            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = name)
        }



    }
}

@Preview(showBackground = true)
@Composable
fun PreviewElementsScreen() {
    HobbyLobbyTheme {
        ElementsScreen(
            description = "Este es el perfil de Oscar. Aquí puedes encontrar información acerca de sus redes sociales y mucho más.",
            ondescriptionChange = {},
            name = "Oscar",
            onNameChange = {},
            onBackClick = {},
            onClearClick = {},
            onPictureChange = {},
            onAddClick = {}
        )
    }
}
