package com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
    onClickNavigation:()-> Unit,
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier,
            onClick = onClickNavigation,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {

            Box(
                modifier = Modifier
                    .size(76.dp)
                    .weight(0.20f)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(0.7f)
            ) {
                Text(
                    text = name, fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringCutter(
                        description,
                        50
                    ), //about 50 letters for having a reason to read the description
                    color = MaterialTheme.colorScheme.inverseSurface,
                )

            }
        }
    }
}