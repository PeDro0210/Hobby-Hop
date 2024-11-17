package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri

data class CreationState(
    var username: String = "",
    var pfpUri: Uri? = null,
    var pfpBitMap: Uri? = null,

    var register: Boolean = false
)