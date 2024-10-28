package com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToBigCommunities
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToRooms
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToSmallCommunities
import com.pedro0210.hobbylobby.presentation.util.stringCutter

@Composable
fun CommunityButton(
    image: String,
    name: String,
    description: String,
    id: String,
    partOfCommunity: Boolean,
    navController: NavController,
    type: CommunityType
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
            Button(
                modifier = Modifier
                    .weight(0.9f),
                onClick = {

                        /*
                        * TODO: assign different navigation depending on the button type
                        *
                        * buttonType: bigCommunity -> navigate to BigCommunity
                        * buttonType: smallCommunity -> navigate to SmallCommunity
                        * buttonType: room -> navigate to Room
                        */
                        when (type){
                           CommunityType.bigCommunity -> navController.navigateToBigCommunities(
                               BigCommunity(
                                   id = id
                               )
                           )
                           CommunityType.smallCommunity -> navController.navigateToSmallCommunities(
                               SmallCommunity(
                                   id = id
                               )
                           )
                           CommunityType.rooms -> navController.navigateToRooms(
                               Rooms(
                                   id = id
                               )
                           )
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {

                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = name,
                        color = Black //I know is a burnt color, but I don't give a damm
                    )
                    Text(
                        text = stringCutter(
                            description,
                            50
                        ), //about 50 letters for having a reason to read the description
                        color = Gray //Same with this f**** one
                    )

                }
            }
        IconButton (
            modifier = Modifier
                .weight(0.1f),
            onClick = { /*TODO: Show popup to exit, if part of community*/ },
        ){
            Icon(
                imageVector = Icons.Default.MoreVert ,
                contentDescription =  "For going to settings"
            )
        }
    }
}