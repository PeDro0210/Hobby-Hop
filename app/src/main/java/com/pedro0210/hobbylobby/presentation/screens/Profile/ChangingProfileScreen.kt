package com.pedro0210.hobbylobby.presentation.view.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.event.CreatorEvent
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToAddSocial
import com.pedro0210.hobbylobby.presentation.screens.Util.LoadingLayout
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.GlobalRoom
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.GlobalSocial
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel

@Composable
fun ChangingProfileScreenRoute(
    viewModel: ProfileViewModel,
    navController: NavController
){
    val state: ProfileCreationState by viewModel.state.collectAsStateWithLifecycle()
    val globalsocial by GlobalSocial.newSocial
    LaunchedEffect(Unit){
        if (globalsocial.name != ""){
            viewModel.onEvent(ProfileEvent.onSocialMediaCreate(globalsocial.name, globalsocial.url, globalsocial.image, globalsocial.new))
        }
    }
    LaunchedEffect(state) {
        if (state.isDoneUploading) {
            navController.popBackStack()
        }

    }

    ChangingProfileScreen(
        state = state,
        navController = navController,
        onAddClick = {navController.navigateToAddSocial()},
        onBackClick = {navController.popBackStack()},
        onClearClick = {viewModel.onEvent(ProfileEvent.onNameChange(""))},
        ondescriptionChange = {viewModel.onEvent(ProfileEvent.onBioChange(it))},
        ondoneClick = {
            viewModel.onEvent(ProfileEvent.onDoneEditing(state.username, state.bio, state.image, state.socials))
            //navController.popBackStack()
        },
        onNameChange = {viewModel.onEvent(ProfileEvent.onNameChange(it))},
        onDeleteSocial = {viewModel.onEvent(ProfileEvent.onSocialMediaDelete(it))},
        onPictureChange = {viewModel.onEvent(ProfileEvent.onPictureChange(it))}
    )
}




@Composable
fun ChangingProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileCreationState,
    ondescriptionChange: (String) -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    ondoneClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onPictureChange: (Uri) -> Unit = {},
    onAddClick: () -> Unit = {},
    onDeleteSocial: (String) -> Unit = {},
    navController: NavController
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                ondoneClick()
            }, modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
        },
        content = { innerPadding ->
            if (state.isLoading) {
                LoadingLayout(modifier = Modifier.fillMaxSize())
            } else {
                ElementsScreen(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    state = state,
                    ondescriptionChange = ondescriptionChange,
                    onNameChange = onNameChange,
                    onBackClick = onBackClick,
                    onClearClick = onClearClick,
                    onPictureChange = onPictureChange,
                    onAddClick = onAddClick,
                    onDeleteSocial = onDeleteSocial
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ElementsScreen(modifier: Modifier = Modifier,
                   state: ProfileCreationState,
                   ondescriptionChange: (String) -> Unit,
                   onNameChange: (String) -> Unit,
                   onBackClick: () -> Unit = {},
                   onClearClick: () -> Unit = {},
                   onPictureChange: (Uri) -> Unit = {},
                   onAddClick: () -> Unit = {},
                   onDeleteSocial: (String) -> Unit = {}
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
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ){
                IconButton(onClick = {onBackClick()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text =  "Perfil")
            }

        })
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
        ){
            Row (modifier = Modifier.fillMaxWidth()){
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                        .size(150.dp)
                        .clip(CircleShape),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    if (state.imageUrl == "") {
                        AsyncImage(model = state.image, contentDescription = "PFP", modifier = Modifier
                            .size(150.dp), contentScale = ContentScale.Crop)
                    } else {
                        AsyncImage(model = state.imageUrl, contentDescription = "PFP", modifier = Modifier
                            .size(150.dp), contentScale = ContentScale.Crop)
                    }

                    IconButton(onClick = {launcher.launch("image/*")},
                        modifier = Modifier.size(100.dp)

                    ) {
                        Icon(
                            Icons.Default.Camera,
                            contentDescription = "Picture",
                            modifier = Modifier.size(50.dp)
                        )

                    }

                }
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = state.username,
                    onValueChange = onNameChange,
                    trailingIcon = {
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Default.Clear, contentDescription = "Add")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextField(value = state.bio, onValueChange = ondescriptionChange, modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface))

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Redes Sociales", style = MaterialTheme.typography.headlineSmall, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Column (modifier = Modifier.padding(4.dp, 0.dp)){
                LazyColumn {
                    items(state.socials.size) { index ->
                        SocialSquare(
                            Text = state.socials[index].name,
                            image = state.socials[index].image,
                            imageUrl = state.socials[index].imageUrl,
                            onDeleteSocial = { onDeleteSocial(state.socials[index].name) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        AddSocial(Text = "Agregar", onAddClick = onAddClick)
                    }

                }

            }


        }


    }

}

@Composable
fun SocialSquare(
    Text: String,
    image: Uri?,
    imageUrl: String,
    onDeleteSocial: () -> Unit = {}
){
    Column {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.65f),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                        .fillMaxWidth(0.35f)
                ) {
                    if (imageUrl == "") {
                        AsyncImage(
                            model = image, contentDescription = "Social", modifier = Modifier
                                .size(75.dp), contentScale = ContentScale.Crop
                        )
                    } else {
                        AsyncImage(
                            model = imageUrl, contentDescription = "Social", modifier = Modifier
                                .size(75.dp), contentScale = ContentScale.Crop
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = Text)
            }
            IconButton(onClick = onDeleteSocial) {
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        HorizontalDivider( color = MaterialTheme.colorScheme.primary, thickness = 0.5.dp)
    }
}

@Composable
fun AddSocial(
    Text: String,
    onAddClick: () -> Unit = {}
){
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
                    .size(65.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary
                    )
                    .fillMaxWidth(0.35f),
                contentAlignment = androidx.compose.ui.Alignment.Center
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






