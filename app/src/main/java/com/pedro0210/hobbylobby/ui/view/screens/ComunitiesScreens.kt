package com.pedro0210.hobbylobby.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.controller.util.Community
import com.pedro0210.hobbylobby.controller.util.generateRandomCommunities
import com.pedro0210.hobbylobby.model.ComunitiesState
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.ui.view.widgets.SearchTopBar

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comunities(
    navController: NavController,
    state: ComunitiesState
){
    //TODO: make the view Model do the take care of the searchText

    Scaffold (
        topBar = {
            SearchTopBar(
                navController = navController ,
                searchText = state.searchText,
                homeScreen = false
            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.35f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                   AsyncImage(
                        model = state.image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(16.dp))
                   )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp),
                    ){
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = state.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text (
                            text = state.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (state.partOfCommunity){
                            Button(
                                onClick = {/*TODO: exit the community*/},
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)                            )
                            {
                                Text(text = "Exit")
                            }
                        }
                        else{
                            Button(
                                onClick = { /*TODO: auth the user for joining the community and redirect to the community screen*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Green) //TODO: change to theme
                            ) {
                                Text(text = "Join")
                            }
                        }

                    }
                }
                    CommunitiesColumns(
                        modifier = Modifier.weight(0.65f),
                        // Use the correct function depending on the screen
                        communities = if (state.bigCommunity) generateRandomCommunities(10) else { generateRandomCommunities(10)  //this will change to the thing above
                             }, // Replace with Firebase functions
                        title = if (state.bigCommunity) "Communities" else "All Users",
                        partOfCommunity = state.partOfCommunity
                    )

            }
        }
    )

}



@Preview(showBackground = true)
@Composable
fun CommRedirectCommPreview() {
    HobbyLobbyTheme {
        val item = generateRandomCommunities(1)[0]
        Comunities(
            navController = rememberNavController(),
            state = ComunitiesState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CommRedirectUsersPreview() {
    HobbyLobbyTheme {
        val item = generateRandomCommunities(1)[0]
        Comunities(
            navController = rememberNavController(),
            state = ComunitiesState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
                bigCommunity = false
                )
            )
        }
    }
