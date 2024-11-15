package com.pedro0210.hobbylobby.presentation.event

import com.pedro0210.hobbylobby.presentation.model.Community

interface CreatorEvent {
    data class onComunityCreate(
        val title: String,
        val description: String,
        val image: String,
        val rooms: List<Community>
    ) : CreatorEvent
    data class onRoomCreate(
        val title: String,
        val description: String,
        val image: String,
    ) : CreatorEvent
    data class onRoomDelete(
        val id: String
    ) : CreatorEvent
    data class onPictureChange(
        val image: String
    ) : CreatorEvent
    data class onNameChange(
        val name: String
    ) : CreatorEvent
    data class onDescriptionChange(
        val description: String
    ) : CreatorEvent




}