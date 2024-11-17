package com.pedro0210.hobbylobby.presentation.state

import android.net.Uri

data class CreationState(
    var username: String = "",
    var pfpUri: Uri? = null,
    var pfpBitMap: Uri? = null,
    override var isLoading: Boolean = true,
    var register: Boolean = false
):IState