package com.pedro0210.hobbylobby.presentation.event

import android.net.Uri
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation

interface CreatorEvent {
    data class onComunityCreate(val title: String, val description: String, val image: Uri?, val rooms: List<CommunityCreation>, val countryName: String) : CreatorEvent
    data class onRoomCreate(val title: String, val description: String, val image: Uri?) : CreatorEvent
    data class onRoomDelete(val title: String): CreatorEvent
    data class onPictureChange(val image: Uri?) : CreatorEvent
    data class onNameChange(val name: String) : CreatorEvent
    data class onDescriptionChange(val description: String) : CreatorEvent
    data class onBigCommunityChange(val bigCommunityName: String) : CreatorEvent
    data class onRoomPictureChange(val image: Uri?) : CreatorEvent
    data class onRoomNameChange(val title: String) : CreatorEvent
    data class onRoomDescriptionChange(val description: String) : CreatorEvent

    //TODO: kill pedro0210



}