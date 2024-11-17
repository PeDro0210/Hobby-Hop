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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.event.ProfileEvent
import com.pedro0210.hobbylobby.presentation.state.ProfileCreationState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.GlobalRoom
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.GlobalSocial
import com.pedro0210.hobbylobby.presentation.viewmodel.profile.ProfileViewModel
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme


@Composable
fun AddingLinksScreenRoute(
    viewModel: ProfileViewModel,
    navController: NavController
){
    //TODO: add state
    val state: ProfileCreationState by viewModel.state.collectAsStateWithLifecycle()

    AddingLinksScreen(
        navController = navController,
        state = state,
        onBackClick = {navController.popBackStack()},
        ondoneClick = {
            GlobalSocial.setSocial(state.socialName, state.socialUrl, state.socialImage)
            navController.popBackStack()},
        onSocialNameChange = {viewModel.onEvent(ProfileEvent.onSocialNameChange(it))},
        onSocialLinkChange = {viewModel.onEvent(ProfileEvent.onSocialUrlChange(it))},
        onClearNameClick = {viewModel.onEvent(ProfileEvent.onSocialNameChange(""))},
        onClearLinkClick = {viewModel.onEvent(ProfileEvent.onSocialUrlChange(""))},
        onPictureChange = {viewModel.onEvent(ProfileEvent.onSocialImageChange(it))}
    )
}


//TODO: need to manage states
@Composable
fun AddingLinksScreen(modifier: Modifier = Modifier,
                      state: ProfileCreationState,
                        onSocialNameChange: (String) -> Unit = {},
                        onSocialLinkChange: (String) -> Unit = {},
                        onBackClick: () -> Unit = {},
                        ondoneClick: () -> Unit = {},
                        onClearNameClick: () -> Unit = {},
                        onClearLinkClick: () -> Unit = {},
                        onPictureChange: (Uri) -> Unit = {},
                      navController: NavController
){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                ondoneClick()
            }) {
                Icon(Icons.Default.Check, contentDescription = null)
            }
        }
    ){
        AddSocialLinksScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            state = state,
            onSocialNameChange = onSocialNameChange,
            onClearNameClick = onClearNameClick,
            onSocialLinkChange = onSocialLinkChange,
            onClearLinkClick = onClearLinkClick,
            onBackClick = onBackClick,
            onPictureChange = onPictureChange
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSocialLinksScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, onPictureChange: (Uri) -> Unit,
                          onSocialNameChange: (String) -> Unit, onClearNameClick: () -> Unit,
                          onSocialLinkChange: (String) -> Unit, onClearLinkClick: () -> Unit,
                            state: ProfileCreationState
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onPictureChange(uri)
        }

    }

    Column(modifier = Modifier.fillMaxSize()){
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
                Text(text =  "Regresar")
            }

        })

        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally

        ){
            Box(modifier = Modifier
                .size(150.dp)
                .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                AsyncImage(model = state.socialImage, contentDescription = "new social image", modifier = Modifier.size(150.dp))
                IconButton(onClick = {launcher.launch("image/*")}) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Picture",
                        modifier = Modifier.size(200.dp)

                    )

                }

            }
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = state.socialName,
                modifier = Modifier.fillMaxWidth(0.87f),
                onValueChange = onSocialNameChange,
                trailingIcon = {
                    IconButton(onClick = onClearNameClick) {
                        Icon(Icons.Default.Clear, contentDescription = "Add")
                    }
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = state.socialUrl,
                modifier = Modifier.fillMaxWidth(0.87f),
                onValueChange = onSocialLinkChange,
                trailingIcon = {
                    IconButton(onClick = onClearLinkClick) {
                        Icon(Icons.Default.Clear, contentDescription = "Add")
                    }
                }
            )
        }
    }

}
