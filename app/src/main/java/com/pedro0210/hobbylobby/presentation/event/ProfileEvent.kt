package com.pedro0210.hobbylobby.presentation.event

import android.net.Uri
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation

interface ProfileEvent {
    data class onDoneEditing(val name: String, val bio: String, val image: Uri?, val social: List<SocialMediaCreation>) : ProfileEvent
    data object onLoadUser: ProfileEvent
    data class onNameChange(val name: String): ProfileEvent
    data class onBioChange(val bio: String): ProfileEvent
    data class onPictureChange(val image: Uri?) : ProfileEvent
    data class onSocialMediaCreate(val socialName: String, val url: String, val image: Uri?, val new: Boolean): ProfileEvent //TODO: Fix this
    data class onSocialMediaDelete(val socialName: String): ProfileEvent
    data class onSocialNameChange(val socialName: String): ProfileEvent
    data class onSocialUrlChange(val url: String): ProfileEvent
    data class onSocialImageChange(val image: Uri?): ProfileEvent


}