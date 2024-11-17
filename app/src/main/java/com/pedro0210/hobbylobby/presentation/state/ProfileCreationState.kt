package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation

data class ProfileCreationState(
    val username: String = "",
    val bio: String = "",
    val image: Uri? = null,
    val imageUrl: String = "",
    val hasError: Boolean = false,
    val isLoading: Boolean = false,
    val isDoneUploading: Boolean = false,
    val hasChangedPfp: Boolean = false,
    val socials: List<SocialMediaCreation> = emptyList(),
    val socialName: String = "",
    val socialUrl: String = "",
    val socialImage: Uri? = null,
    val socualNew: Boolean = false,


)