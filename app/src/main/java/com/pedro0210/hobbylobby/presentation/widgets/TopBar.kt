package com.pedro0210.hobbylobby.presentation.view.screens.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    homeScreen:Boolean,
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

       },
        actions = {
            IconButton (
                onClick = { navController.navigateToSettings()},
            ){
                Icon(
                    imageVector = Icons.Default.MoreVert ,
                    contentDescription =  "For going to settings"
                )
            }
        }
    )
}