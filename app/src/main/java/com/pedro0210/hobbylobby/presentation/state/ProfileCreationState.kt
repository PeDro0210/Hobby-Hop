package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri

data class ProfileCreationState(
    var username: String = "",
    var pfpUri: Uri? = null,
    var pfpBitMap: Uri? = null
)