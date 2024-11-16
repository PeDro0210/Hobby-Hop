package com.pedro0210.hobbylobby.presentation.state

import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType

data class AdminCommunitiesState(
    val communities: List<Community>,
    val type: CommunityType,
)
