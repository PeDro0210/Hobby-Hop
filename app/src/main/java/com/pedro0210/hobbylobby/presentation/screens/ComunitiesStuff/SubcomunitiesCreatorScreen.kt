package com.pedro0210.hobbylobby.presentation.view.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.event.CreatorEvent
import com.pedro0210.hobbylobby.presentation.state.CreatorScreenState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CreatorViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.GlobalRoom
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun SubcommunitiesCreatorRoute(
    viewModel: CreatorViewModel = viewModel(factory = CreatorViewModel.Factory),
    navController: NavController
){
    val state: CreatorScreenState by viewModel.state.collectAsStateWithLifecycle()

    SubcommunitiesCreatorScreen(
        state = state,
        navController = navController,
        onPictureChange = {viewModel.onEvent(CreatorEvent.onRoomPictureChange(it))},
        onNameChange = {viewModel.onEvent(CreatorEvent.onRoomNameChange(it))},
        onClearClick = {viewModel.onEvent(CreatorEvent.onRoomNameChange(""))},
        ondescriptionChange = {viewModel.onEvent(CreatorEvent.onRoomDescriptionChange(it))},
        ondoneClick = {
            GlobalRoom.setRoom(state.roomTitle, state.roomDescription, state.roomImage)
            navController.popBackStack()
        },
        onBackClick = {navController.popBackStack()
            println(state.rooms)}

    )
}


@Composable
fun SubcommunitiesCreatorScreen(
    state: CreatorScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPictureChange: (Uri) -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    ondescriptionChange: (String) -> Unit = {},
    ondoneClick: () -> Unit = {},
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
            state = state,
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            onBackClick = onBackClick,
            onPictureChange = onPictureChange,
            onNameChange = onNameChange,
            onClearClick = onClearClick,
            ondescriptionChange = ondescriptionChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubcommunitiesCreator(
    state: CreatorScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPictureChange: (Uri) -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    ondescriptionChange: (String) -> Unit = {}
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onPictureChange(uri)
        }

    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
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
                        .size(150.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    AsyncImage(
                        model = state.roomImage,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                    )
                    IconButton(onClick = {launcher.launch("image/*")}) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Picture",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = state.roomTitle,
                    onValueChange = onNameChange,
                    trailingIcon = {
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Default.Clear, contentDescription = "delete")
                        }
                    },
                    placeholder = { Text("Name") },
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = state.roomDescription,
                onValueChange = ondescriptionChange,
                placeholder = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

