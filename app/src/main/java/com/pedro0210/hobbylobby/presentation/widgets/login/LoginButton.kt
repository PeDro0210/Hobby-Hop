package com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(
    description:String,
    image:Int,
    loginFunction: () -> Unit
){
    IconButton(
        onClick = loginFunction,
        modifier = Modifier
            .background(
                White,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        Icon(
            painter = painterResource(id = image), // replace with your Google logo resource
            contentDescription = description,
            modifier = Modifier.size(50.dp)
        )
    }

}