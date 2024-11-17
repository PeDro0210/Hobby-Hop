package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation
import com.pedro0210.hobbylobby.presentation.model.CommunityType


object GlobalRoom {
    var newRoom: MutableState<CommunityCreation> = mutableStateOf(CommunityCreation(
        title = "",
        description = "",
        image = null,
        partOfCommunity = false,
        type = CommunityType.rooms,
        id = "N/A"
        )
    )
        private set

    fun setRoom(title: String, description: String, image: Uri?) {
        newRoom.value = CommunityCreation(
            title = title,
            description = description,
            image = image,
            partOfCommunity = false,
            type = CommunityType.rooms,
            id = "N/A"
        )

    }

}