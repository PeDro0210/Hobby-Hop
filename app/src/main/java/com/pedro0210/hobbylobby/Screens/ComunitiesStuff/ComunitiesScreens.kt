package com.pedro0210.hobbylobby.Screens.ComunitiesStuff

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.model.ButtonType
import com.pedro0210.hobbylobby.presentation.state.ComunitiesScreenState
import com.pedro0210.hobbylobby.presentation.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar

@SuppressLint("UnrememberedMutableState")
@Composable
fun Communities(
    navController: NavController,
    state: ComunitiesScreenState
){
    //TODO: make the view Model do the take care of the searchText

    Scaffold (
        topBar = {
            TopBar(
                navController = navController ,
                homeScreen = false,
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
                        communities = state.communities,
                        title = if (state.bigCommunity) "Communities" else "All Users",
                        partOfCommunity = state.partOfCommunity,
                        navController = navController,
                        buttonType = state.buttonType
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
        Communities(
            navController = rememberNavController(),
            state = ComunitiesScreenState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
                communities = generateRandomCommunities(10),
                buttonType = ButtonType.bigCommunity
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CommRedirectUsersPreview() {
    HobbyLobbyTheme {
        val item = generateRandomCommunities(1)[0]
        Communities(
            navController = rememberNavController(),
            state = ComunitiesScreenState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
                bigCommunity = false,
                communities = generateRandomCommunities(10),
                buttonType = ButtonType.smallCommunity
                )
            )
        }
    }
