package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.Community

data class HomeScreenState(
    val countries:List<Community>,
    val ownCommunities:List<Community>
)