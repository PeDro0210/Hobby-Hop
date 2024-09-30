package com.pedro0210.hobbylobby.ui.view.widgets.buttons

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
import coil.compose.AsyncImage
import com.pedro0210.hobbylobby.controller.util.stringCutter

@Composable
fun CommunityButton(
    image: String,
    name: String,
    description: String,
    partOfCommunity: Boolean
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
            Button(
                modifier = Modifier
                    .weight(0.9f),
                onClick = { /*TODO: create instance of community with all attributes*/ },
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
            onClick = { /*TODO: Show popup to exit*/ },
        ){
            Icon(
                imageVector = Icons.Default.MoreVert ,
                contentDescription =  "For going to settings"
            )
        }
    }
}