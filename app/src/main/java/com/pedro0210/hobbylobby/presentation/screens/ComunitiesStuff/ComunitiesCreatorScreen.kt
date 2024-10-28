package com.pedro0210.hobbylobby.presentation.view.screens

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToCreateSmallCommunities
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CommunitiesViewModel
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme


@Composable
fun CommunitiesCreatorRoute(
    //TODO: create another viewmodel for this
    navController: NavController
){
    //TODO: Add state

    CommunitiesCreatorScreen(
        navController = navController,
        onAddClick = {navController.navigateToCreateSmallCommunities()}
    )

}


//TODO: add state
@Composable
fun CommunitiesCreatorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}, //for nav
    onPictureChange: () -> Unit = {},
    name: String = "", //for the meanwhile
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    description: String = "",
    ondescriptionChange: (String) -> Unit = {},
    ondoneClick: () -> Unit = {}, //For nav
    onDeleteSubC: () -> Unit = {},
    onAddClick: () -> Unit = {}, //For nav
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
        CommunitiesCreator(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            onBackClick = onBackClick,
            onPictureChange = onPictureChange,
            name = name,
            onNameChange = onNameChange,
            onClearClick = onClearClick,
            description = description,
            ondescriptionChange = ondescriptionChange,
            onDeleteSubC = onDeleteSubC,
            onAddClick = onAddClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesCreator(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPictureChange: () -> Unit = {},
    name: String = "",
    onNameChange: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    description: String = "",
    ondescriptionChange: (String) -> Unit = {},
    onDeleteSubC: () -> Unit = {},
    onAddClick: () -> Unit = {}
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
                            color = MaterialTheme.colorScheme.primary
                        )
                        .size(150.dp)
                        .clip(CircleShape),
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
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = description,
                onValueChange = ondescriptionChange,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 2.dp)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Administrar Subcomunidades:")

            Spacer(modifier = Modifier.height(8.dp))
            Column (modifier = Modifier.padding(4.dp, 0.dp)){
                LazyColumn {
                    items(2) {
                        SubcommunitySquare("SubComunidad", onDeleteSubC = onDeleteSubC)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
                AddSubcommuinity(Text = "Agregar", onAddClick = onAddClick)

            }

        }
    }
}

@Composable
fun SubcommunitySquare(
    Text: String,
    onDeleteSubC: () -> Unit = {}
){
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
            Text(text = Text)
        }
        IconButton(onClick = onDeleteSubC) {
            Icon(Icons.Default.Close, contentDescription = "Delete")
        }



    }
}

@Composable
fun AddSubcommuinity(
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
                        color = MaterialTheme.colorScheme.primary
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
