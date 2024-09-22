package com.pedro0210.hobbylobby.layoutsettings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSocialLinksScreen(modifier: Modifier = Modifier, onbackClick: () -> Unit,
                         socialName: String, onsocialNameChange: (String) -> Unit,
                         socialLink: String, onsocialLinkChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()){
        TopAppBar(title = {
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ){
                IconButton(onClick = {onbackClick()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text =  "Regresar")
            }

        })

        Column (modifier = Modifier.fillMaxSize()){
            Box(modifier = Modifier) {
            }
            OutlinedTextField(value = socialName, onValueChange = onsocialNameChange)
            OutlinedTextField(value = socialLink, onValueChange = onsocialLinkChange)
        }
    }


}