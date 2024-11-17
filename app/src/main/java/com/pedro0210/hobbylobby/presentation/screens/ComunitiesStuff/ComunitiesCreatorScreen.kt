package com.pedro0210.hobbylobby.presentation.view.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToCreateSmallCommunities
import com.pedro0210.hobbylobby.presentation.state.CreatorScreenState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CreatorViewModel
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.GlobalRoom
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme


@Composable
fun CommunitiesCreatorRoute(

    viewModel: CreatorViewModel = viewModel(factory = CreatorViewModel.Factory),
    navController: NavController
){

    val state: CreatorScreenState by viewModel.state.collectAsStateWithLifecycle()
    val globalroom by GlobalRoom.newRoom
    //var globalroom by remember { mutableStateOf(GlobalRoom.newRoom) }
    LaunchedEffect(Unit){
        if (globalroom.title != ""){
            viewModel.onEvent(CreatorEvent.onRoomCreate(globalroom.title, globalroom.description, globalroom.image))
        }
    }

    CommunitiesCreatorScreen(
        navController = navController,
        onAddClick = {navController.navigateToCreateSmallCommunities()},
        state = state,
        onPictureChange = {viewModel.onEvent(CreatorEvent.onPictureChange(it))}, //TODO:fix
        onNameChange = {viewModel.onEvent(CreatorEvent.onNameChange(it))},
        ondescriptionChange = {viewModel.onEvent(CreatorEvent.onDescriptionChange(it))},
        ondoneClick = {viewModel.onEvent(CreatorEvent.onComunityCreate(state.title, state.description, state.image, state.rooms, state.bigCommunityName))
                        while(true){
                            if (state.isLoading == false){
                                navController.popBackStack()
                                break
                            }
                        }
        },
        onBigCommunityChange = {viewModel.onEvent(CreatorEvent.onBigCommunityChange(it))},
        onDeleteSubC = {viewModel.onEvent(CreatorEvent.onRoomDelete(it))},
        onBackClick = {navController.popBackStack()}

    )

}


//TODO: add state
@Composable
fun CommunitiesCreatorScreen(
    onBackClick: () -> Unit = {}, //for nav
    onPictureChange: (Uri) -> Unit = {}, //IDK
    onNameChange: (String) -> Unit = {}, //IDK
    ondescriptionChange: (String) -> Unit = {},//IDK
    ondoneClick: () -> Unit = {}, //For nav
    onDeleteSubC: (String) -> Unit = {}, //IDK
    onAddClick: () -> Unit = {}, //For nav
    onBigCommunityChange: (String) -> Unit ={},
    navController: NavController,
    state: CreatorScreenState
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
        CommunitiesCreator(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            onBackClick = onBackClick,
            onPictureChange = onPictureChange,
            onNameChange = onNameChange,
            ondescriptionChange = ondescriptionChange,
            onDeleteSubC = onDeleteSubC,
            onAddClick = onAddClick,
            state = state,
            onBigCommunityChange = onBigCommunityChange
        )
    }
}


@Composable
fun SubcommunitySquare(Text: String, image: Uri?, onDeleteSubC: () -> Unit) {
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
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary
                    )
                    .fillMaxWidth(0.35f),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                AsyncImage(model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = Text)
        }
        IconButton(onClick = onDeleteSubC) {
            Icon(Icons.Default.Close, contentDescription = "Delete")
        }



    }


}

@Composable
fun AddSubcommuinity(Text: String, onAddClick: () -> Unit) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { onAddClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ){
        Row(modifier = Modifier.fillMaxWidth(0.65f),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary
                    )
                    .fillMaxWidth(0.35f)
                ,contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Next",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = Text)
        }
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next")

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesCreator(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}, //for nav
    onPictureChange: (Uri) -> Unit = {}, //IDK
    onNameChange: (String) -> Unit = {}, //IDK
    onBigCommunityChange: (String) -> Unit = {},
    ondescriptionChange: (String) -> Unit = {},//IDK
    onDeleteSubC: (String) -> Unit = {}, //IDK
    onAddClick: () -> Unit = {}, //For nav
    state: CreatorScreenState
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onPictureChange(uri)
        }

    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
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
                Text(text = "Menu")
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
                        model = state.image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)

                    )
                    IconButton(onClick = {launcher.launch("image/*")}
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Picture",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column (modifier = Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = onNameChange,
                        )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state.bigCommunityName,
                        onValueChange = onBigCommunityChange
                    )

                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.description,
                onValueChange = ondescriptionChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 2.dp)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Administrar Subcomunidades:")

            Spacer(modifier = Modifier.height(8.dp))
            Column (modifier = Modifier.padding(4.dp, 0.dp)){
                LazyColumn {
                    items(state.rooms.size) { index ->
                        SubcommunitySquare(
                            Text = state.rooms[index].title,
                            image = state.rooms[index].image,
                            onDeleteSubC = { onDeleteSubC(state.rooms[index].title) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        AddSubcommuinity(Text = "Agregar Subcomunidad", onAddClick = onAddClick)
                    }
                }



            }

        }
    }
}





