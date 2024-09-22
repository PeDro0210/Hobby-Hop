package com.pedro0210.hobbylobby.layoutsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

@Composable
fun ChanginProfileData(modifier: Modifier = Modifier) {
    var description = "Descripcion"
    var name = "Nombre"
    ChanginProfileScreen(
        modifier = modifier,
        description = description,
        ondescriptionChange = { description = it },
        name = name,
        onnameChange = { name = it },
        onbackClick = {},
        ondoneClick = {}
    )

}

@Composable
fun ChanginProfileScreen(modifier: Modifier = Modifier,
                         description: String,
                         ondescriptionChange: (String) -> Unit,
                         name: String,
                         onnameChange: (String) -> Unit,
                         onbackClick: () -> Unit = {},
                         ondoneClick: () -> Unit = {}){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                ondoneClick()
            }) {
                Icon(Icons.Default.Check, contentDescription = null)
            }
        }
    ){
        ElementsScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            description = description,
            ondescriptionChange = ondescriptionChange,
            name = name,
            onnameChange = onnameChange,
            onbackClick = onbackClick
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ElementsScreen(modifier: Modifier = Modifier,
                         description: String,
                         ondescriptionChange: (String) -> Unit,
                         name: String,
                         onnameChange: (String) -> Unit,
                         onbackClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
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
                Text(text =  "Perfil")
            }

        })
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
        ){
            Row (modifier = Modifier.fillMaxWidth()){
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )

                        .size(150.dp)
                        .clip(CircleShape)
                ) {

                }
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(value = name, onValueChange = onnameChange)
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextField(value = description, onValueChange = ondescriptionChange, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Redes Sociales", style = MaterialTheme.typography.headlineSmall, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Column (modifier = Modifier.padding(24.dp, 0.dp)){
                LazyColumn {
                    items(2) {
                        SocialSquare("Red Social")
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
                AddSocial(Text = "Agregar")

            }


    }


}

}

@Composable
fun SocialSquare(
    Text: String
){
    Box(modifier = Modifier
        .height(125.dp)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(16.dp)
        )
        .fillMaxWidth(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Box(modifier = Modifier
                .fillMaxHeight(0.8f)
                .background(
                    color = MaterialTheme.colorScheme.secondary
                )
                .fillMaxWidth(0.35f)
            ){

            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = Text)

        }
    }
}

@Composable
fun AddSocial(
    Text: String
){
    Box(modifier = Modifier
        .height(125.dp)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(16.dp)
        )
        .fillMaxWidth(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Box(modifier = Modifier
                .fillMaxHeight(0.8f)
                .background(
                    color = MaterialTheme.colorScheme.primary
                )
                .fillMaxWidth(0.35f),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ){
                Icon(Icons.Default.Add, contentDescription = "Next", modifier = Modifier.size(50.dp))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = Text)
            Spacer(modifier = Modifier.padding(40.dp, 0.dp))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next")

        }
    }
}

@Preview
@Composable
private fun PreviewChanginProfileScreen() {
    HobbyLobbyTheme {
        Surface {
            ChanginProfileData(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

