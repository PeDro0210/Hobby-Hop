package com.pedro0210.hobbylobby.presentation.view.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme


@Composable
fun AddingLinksScreenRoute(
    //TODO: add viewmodel when created,
    navController: NavController
){
    //TODO: add state

    AddingLinksScreen(
        navController = navController
    )
}


//TODO: need to manage states
@Composable
fun AddingLinksScreen(modifier: Modifier = Modifier,
                        socialName: String = "",
                        onSocialNameChange: (String) -> Unit = {},
                        socialLink: String = "",
                        onSocialLinkChange: (String) -> Unit = {},
                        onBackClick: () -> Unit = {},
                        ondoneClick: () -> Unit = {},
                        onClearNameClick: () -> Unit = {},
                        onClearLinkClick: () -> Unit = {},
                        onPictureChange: () -> Unit = {},
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
            socialName = socialName,
            onSocialNameChange = onSocialNameChange,
            onClearNameClick = onClearNameClick,
            socialLink = socialLink,
            onSocialLinkChange = onSocialLinkChange,
            onClearLinkClick = onClearLinkClick,
            onBackClick = onBackClick,
            onPictureChange = onPictureChange
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSocialLinksScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, onPictureChange: () -> Unit,
                         socialName: String, onSocialNameChange: (String) -> Unit, onClearNameClick: () -> Unit,
                         socialLink: String, onSocialLinkChange: (String) -> Unit, onClearLinkClick: () -> Unit
) {
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
                .size(200.dp)
                .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                IconButton(onClick = onPictureChange) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Picture",
                        modifier = Modifier.size(200.dp)

                    )

                }

            }
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = socialName,
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
                value = socialLink,
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
