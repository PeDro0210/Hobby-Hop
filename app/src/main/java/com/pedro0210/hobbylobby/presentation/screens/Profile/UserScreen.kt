package com.pedro0210.hobbylobby.presentation.screens.Profile

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.state.UserState
import com.pedro0210.hobbylobby.presentation.util.NoRippleTheme
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.UserViewModel


@Composable
fun ProfileScreenRoute(
    viewModel: UserViewModel,
    navController: NavController
){
    val state: UserState by viewModel.state.collectAsStateWithLifecycle()

    UserScreen(
        state = state,
        onBackClick = { navController.popBackStack() },
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    onBackClick: () -> Unit,
    state: UserState,
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
                Text(text = "${state.user.name} profile")
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
                verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    model = state.user.image,
                    contentDescription = "User pfp"
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = state.user.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            // Descripción
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.user.description, style = MaterialTheme.typography.bodyLarge)

            // Sección de redes sociales
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "${state.user.name}'s social media:", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = state.user.socialMedia ?: emptyList()) { socialmedia: SocialMedia ->
                    SocialSquare(
                        name = socialmedia.name,
                        url = socialmedia.url,
                        pfp = socialmedia.image
                        )
                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    }
}

@Composable
fun SocialSquare(
    name: String,
    url: String,
    pfp: String,
) {
    val context = LocalContext.current


    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url.trim())
                ) //the trim is for sanitizing the f****** url
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.65f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary
                            )
                            .fillMaxWidth(0.35f),
                        contentAlignment = Alignment.Center
                    ) {

                        AsyncImage(
                            model = pfp,
                            contentDescription = "Social media icon",
                        )

                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = name,
                        color = Color.Black,
                        )
                }

            }
        }
    }
}


