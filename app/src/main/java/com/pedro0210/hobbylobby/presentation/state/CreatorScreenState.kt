package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation


data class CreatorScreenState(
    val id:String = "",
    val image: Uri? = null,
    val title: String = "",
    val description: String = "",
    val bigCommunityName: String = "",
    val rooms: List<CommunityCreation> = emptyList(),
    val roomImage: Uri? = null,
    val roomTitle: String = "",
    val roomDescription: String = "",
    val hasError: Boolean = false,
    override var isLoading: Boolean = false,
    val isDoneUploading: Boolean = false,


):IState