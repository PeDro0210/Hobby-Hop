package com.pedro0210.hobbylobby.presentation.screens.ComunitiesStuff

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.state.ComunitiesScreenState
import com.pedro0210.hobbylobby.presentation.util.generateRandomCommunities
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.ui.view.widgets.CommunitiesColumns
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.TopBar
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.CommunitiesViewModel
import com.pedro0210.hobbylobby.presentation.widgets.communities.util.routingNormalCommunities

@Composable
fun CommunitiesRoute(
    viewModel: CommunitiesViewModel,
    navController: NavController,
){
    val state: ComunitiesScreenState by viewModel.state.collectAsStateWithLifecycle()

    CommunitiesScreen(
        navController = navController,
        state = state,
        onClickNavigation = {image, name, description, id, partOfCommunity, navController, type ->
            routingNormalCommunities(
                image = image,
                name = name,
                description = description,
                id = id,
                partOfCommunity = partOfCommunity,
                navController = navController,
                type = type
            )
        }
    )

}



@SuppressLint("UnrememberedMutableState")
@Composable
fun CommunitiesScreen(
    navController: NavController,
    state: ComunitiesScreenState,
    onClickNavigation: (
        image: String,
        name: String,
        description: String,
        id: String,
        partOfCommunity: Boolean,
        navController: NavController,
        type: CommunityType) -> Unit
){

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
//                        if (state.partOfCommunity){
//                            Button(
//                                onClick = {/*TODO: exit the community*/},
//                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)                            )
//                            {
//                                Text(text = "Exit")
//                            }
//                        }
//                        else{
//                            Button(
//                                onClick = { /*TODO: auth the user for joining the community and redirect to the community screen*/ },
//                                colors = ButtonDefaults.buttonColors(containerColor = Color.Green) //TODO: change to theme
//                            ) {
//                                Text(text = "Join")
//                            }
//                        }

                    }
                }
                    CommunitiesColumns(
                        modifier = Modifier.weight(0.65f),
                        communities = state.communities,
                        title = if (state.bigCommunity) "Communities" else "Rooms",
                        partOfCommunity = state.partOfCommunity,
                        navController = navController,
                        onClickNavigation = onClickNavigation
                    )

            }
        }
    )

}



@Preview(showBackground = true)
@Composable
fun CommRedirectCommPreview() {
    HobbyLobbyTheme {
        val item = generateRandomCommunities(1, CommunityType.country)[0]
        CommunitiesScreen(
            navController = rememberNavController(),
            state = ComunitiesScreenState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
                communities = generateRandomCommunities(
                        n = 10,
                        type = CommunityType.country
                    ),
            ),
            onClickNavigation = {image, name, description, id, partOfCommunity, navController, type ->}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CommRedirectUsersPreview() {
    HobbyLobbyTheme {
        val item = generateRandomCommunities(1, CommunityType.communities)[0]
        CommunitiesScreen(
            navController = rememberNavController(),
            state = ComunitiesScreenState(
                image = item.image,
                title = item.title,
                description = item.description,
                id = item.id,
                partOfCommunity = item.partOfCommunity,
                bigCommunity = false,
                communities = generateRandomCommunities(
                    n = 10,
                    type = CommunityType.communities
                    ),
                ),
            onClickNavigation = {image, name, description, id, partOfCommunity, navController, type ->}
            )
        }
    }
