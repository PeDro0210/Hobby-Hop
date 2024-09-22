package com.pedro0210.hobbylobby.ui.view.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pedro0210.hobbylobby.controller.util.Community
import com.pedro0210.hobbylobby.ui.view.widgets.buttons.CommunityButton


@Composable
fun CommunitiesColumns(
    modifier: Modifier,
    communities: List<Community>,
    title: String
){
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.
        padding(8.dp))
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp
        )
        LazyColumn(
            modifier = Modifier
        ) {
            items(communities) { item -> //TODO: change to real function from the firebase database
                CommunityButton(
                    image = item.image ,
                    name = item.title,
                    description = item.description,
                    partOfCommunity =  item.partOfCommunity
                )
            }
        }
        Spacer(modifier = Modifier.
        padding(8.dp))
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp
        )

    }
}