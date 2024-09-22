package com.pedro0210.hobbylobby.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.ui.view.widgets.buttons.LoginButton

@Composable
fun Login(
    navController: NavController
){

    Scaffold (
        content = { paddingValues ->
            var buttonText by remember { mutableStateOf("Login") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(264.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { checked ->
                                buttonText = if (checked) {
                                    "Join Us"
                                } else {
                                    "Welcome Back"
                                }

                            }
                        )
                        Text("Login")

                        Spacer(modifier = Modifier.weight(1f))

                        Button(onClick = { /* TODO: Handle login */ }) {
                            Text(buttonText)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                // Join Using Section

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        color = Color.Red,
                        thickness = 2.dp,
                        modifier = Modifier
                            .weight(0.75f)
                    )

                    // "Join Using" text
                    Text(
                        text = "Join Using",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // Second red line
                    HorizontalDivider(
                        color = Color.Red,
                        thickness = 2.dp,
                        modifier = Modifier
                            .weight(0.75f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onPrimaryContainer,
                            shape = RoundedCornerShape(30.dp)
                        ) // light blue background with rounded corners
                        .padding(16.dp)
                ) {

                LoginButton(description = "Google", image = R.drawable.google ) {
                   //TODO: add the login
                }

                LoginButton(description = "Meta", image = R.drawable.meta ) {
                    //TODO: add the login
                }

                LoginButton(description = "X", image = R.drawable.x ) {
                    //TODO: add the login
                }
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    HobbyLobbyTheme {
        Login(navController = rememberNavController())
    }
}