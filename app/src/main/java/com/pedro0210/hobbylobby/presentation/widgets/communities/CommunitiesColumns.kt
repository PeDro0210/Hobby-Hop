package com.pedro0210.hobbylobby.presentation.ui.view.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons.CommunityButton

@Composable
fun CommunitiesColumns(
    modifier: Modifier,
    communities: List<Community>,
    title: String,
    partOfCommunity: Boolean,
    navController: NavController,
    onClickNavigation: (
        image: String,
        name: String,
        description: String,
        id: String,
        partOfCommunity: Boolean,
        navController: NavController,
        type: CommunityType) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp
        )
        
        if (partOfCommunity) {
            LazyColumn(
                modifier = Modifier
            ) {
                items(communities) { item ->  //TODO: add the firebase function later
                    CommunityButton(
                        image = item.image,
                        name = item.title,
                        description = item.description,
                        onClickNavigation = {
                            onClickNavigation(
                                item.image,
                                item.title,
                                item.description,
                                item.id,
                                partOfCommunity,
                                navController,
                                item.type
                            )
                        }

                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked",
                    modifier = Modifier.size(64.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Blocked",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

    }
}