package com.pedro0210.hobbylobby.presentation.model

import android.net.Uri

data class Community( //ths structure of this will almost be the same
    val title:String,
    val description:String,
    val image: String,
    val partOfCommunity:Boolean,
    val type:CommunityType,
    val id: String,
    val isAdmin:Boolean = false
)