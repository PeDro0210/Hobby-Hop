package com.pedro0210.hobbylobby.ui.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    navController: NavController,
    searchText: MutableState<String>,
    homeScreen:Boolean
) {
    TopAppBar(
        navigationIcon = {
            if (!homeScreen){
               IconButton(
                   onClick = { 
                       navController.popBackStack() 
                   }
               ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack ,
                    contentDescription =  "Back"
                )
               }
            }
        },
        title = {
                TextField( //TODO: Make this transparent, without borders, and to be simplest possible
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Bar",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                                  },
                    modifier = Modifier
                        .height(32.dp) //for squishing the bar
                        .padding(start = 8.dp),
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp),
                )
        },
        actions = {
            IconButton (
                onClick = { /*TODO: redirect to the settings screen*/ },
            ){
                Icon(
                    imageVector = Icons.Default.MoreVert ,
                    contentDescription =  "For going to settings"
                )
            }
        }
    )
}