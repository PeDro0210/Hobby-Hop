package com.pedro0210.hobbylobby.presentation.model

import android.net.Uri

data class CommunityCreation( //ths structure of this will almost be the same
    val title:String,
    val description:String,
    val image: Uri?,
    val partOfCommunity:Boolean,
    val type:CommunityType,
    val id: String
)