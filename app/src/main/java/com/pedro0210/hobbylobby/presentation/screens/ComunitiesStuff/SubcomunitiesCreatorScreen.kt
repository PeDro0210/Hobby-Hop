package com.pedro0210.hobbylobby.presentation.view.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun SubcommunitiesCreatorRoute(
    //TODO: create another viewmodel for this
    navController: NavController
){
    //TODO: add state

    SubcommunitiesCreatorScreen(
        navController = navController
    )
}


@Composable
fun SubcommunitiesCreatorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPictureChange: () -> Unit = {},
    name: String = "", //for the meanwhile
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    description: String = "", //for the meanwhile
    ondescriptionChange: (String) -> Unit = {},
    ondoneClick: () -> Unit = {},
    image: String = "", //for the meanwhile
    navController: NavController


){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                ondoneClick()
            }, modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
        }
    ){
        SubcommunitiesCreator(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            onBackClick = onBackClick,
            onPictureChange = onPictureChange,
            name = name,
            onNameChange = onNameChange,
            onClearClick = onClearClick,
            description = description,
            image = image,
            ondescriptionChange = ondescriptionChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubcommunitiesCreator(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPictureChange: () -> Unit = {},
    name: String = "",
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    description: String = "",
    image: String = "",
    ondescriptionChange: (String) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Comunidades")
            }
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 0.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary
                        )
                        .size(150.dp)
                        .clip(CircleShape),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(
                                RoundedCornerShape(16.dp)

                            )
                    )
                    IconButton(onClick = onPictureChange) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Picture",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    trailingIcon = {
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Default.Clear, contentDescription = "Add")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = description,
                onValueChange = ondescriptionChange,
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

@Preview
@Composable
fun SubcommunitiesCreatorPreview() {
    HobbyLobbyTheme {
        SubcommunitiesCreatorScreen(
            name = "Subcomunidad",
            onNameChange = {},
            onClearClick = {},
            description = "Descripcion de la subcomunidad",
            ondescriptionChange = {},
            ondoneClick = {},
            navController = NavController(LocalContext.current))
    }
}