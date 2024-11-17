package com.pedro0210.hobbylobby.presentation.viewmodel.profile

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation


object GlobalSocial {
    var newSocial: MutableState<SocialMediaCreation> = mutableStateOf(SocialMediaCreation(
        name = "",
        image = null,
        url = "",
        new = true,
        imageUrl = ""
        )
    )
        private set

    fun setSocial(name: String, url: String, image: Uri?) {
        newSocial.value = SocialMediaCreation(
            name = name,
            url = url,
            image = image,
            new = true,
            imageUrl = ""
        )

    }

}