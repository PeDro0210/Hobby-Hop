package com.pedro0210.hobbylobby.presentation.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons.LoginButton

@Composable
fun Login(
    navController: NavController,
    state: LoginScreenState,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLoginClick: () -> Unit
){ //TODO: add all states from the loginState somehow
    Scaffold (
        content = { paddingValues ->
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
                        value = state.email,
                        onValueChange = onEmailChange,
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = onPasswordChange,
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
                            onCheckedChange = { checked -> //TODO: also make that the view modle takes care of this
                                state.buttonText = if (checked) {
                                    "Join Us"
                                } else {
                                    "Welcome Back"
                                }

                            }
                        )
                        Text("Login")

                        Spacer(modifier = Modifier.weight(1f))

                        Button(onClick = { /* TODO: Handle login */ }) {
                            Text(state.buttonText)
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

                    val logos: List<Int> = listOf(
                        R.drawable.google,
                        R.drawable.meta,
                        R.drawable.x
                    )

                    for (logo in logos) {
                        LoginButton(description = "Google", image = logo) {
                            //TODO: add the login, with the viewmodel
                        }
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
        Login(
            navController = rememberNavController(),
            state = LoginScreenState(),
            ///TODO: add the view model
            onPasswordChange = {},
            onLoginClick = {},
            onEmailChange = {}
        )
    }
}