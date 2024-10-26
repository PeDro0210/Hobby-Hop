package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.ButtonType
import com.pedro0210.hobbylobby.presentation.model.Community

data class ComunitiesScreenState(
    val id:String,
    val image: String,
    val title: String,
    val description: String,
    val partOfCommunity: Boolean,
    val bigCommunity: Boolean = true,

    val communities: List<Community>,
    val buttonType: ButtonType
)