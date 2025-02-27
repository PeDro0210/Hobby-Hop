package com.pedro0210.hobbylobby.presentation.screens.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.domain.util.AuthAction
import com.pedro0210.hobbylobby.domain.util.LoginEnum
import com.pedro0210.hobbylobby.presentation.navigation.AuthDestionation
import com.pedro0210.hobbylobby.presentation.navigation.Home
import com.pedro0210.hobbylobby.presentation.navigation.SignUp
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateFromLogin
import com.pedro0210.hobbylobby.presentation.state.LoginScreenState
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme
import com.pedro0210.hobbylobby.presentation.view.screens.widgets.buttons.LoginButton
import com.pedro0210.hobbylobby.presentation.viewmodel.login.AuthViewModel


@Composable
fun LoginRoute(
    viewModel: AuthViewModel,
    navController: NavController
){
    val state: LoginScreenState by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        if (!state.hasError) {
            navController.navigateFromLogin(state.navDestination)
        }
    }

    Login(
        navController = navController,
        state = state,
        onPasswordChange = { viewModel.changePassword(it)  },
        onEmailChange = { viewModel.changeEmail(it) },
        onLoginClick = { viewModel.login(it) },
        onBoxChecked = { viewModel.checkBox(it) },
        onChangeButtonText = { viewModel.changeButtonText(it)},
        onChangeNavDestination = {viewModel.changeNavDestination(it)}
    )
}

@Composable
fun Login(
    navController: NavController,
    state: LoginScreenState,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLoginClick: (AuthAction) -> Unit,
    onBoxChecked: (Boolean) -> Unit,
    onChangeButtonText: (String) -> Unit,
    onChangeNavDestination: (AuthDestionation) -> Unit
){
    val isDarkTheme = isSystemInDarkTheme()
    Scaffold (
        content = { paddingValues ->

            if (!state.isLogged) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.background)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Box(
                        modifier = Modifier
                            .size(264.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
                                    radius = with(LocalDensity.current) { 132.dp.toPx() }
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                       Image(
                           painter = painterResource(id = R.drawable.tlogo),
                           contentDescription = null,
                           modifier = Modifier.size(256.dp)
                       )


                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column (modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp)) {


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
                                    checked = state.boxChecked,
                                    onCheckedChange = { checked ->
                                        onBoxChecked(checked)
                                        if (checked) {
                                            onChangeButtonText("Welcome back")
                                            onChangeNavDestination(Home)
                                        } else {
                                            onChangeButtonText("Join Us")
                                            onChangeNavDestination(SignUp)
                                        }
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = MaterialTheme.colorScheme.secondary,
                                        uncheckedColor = MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Text("Login")

                                Spacer(modifier = Modifier.weight(1f))

                                Button(
                                    onClick = {
                                        onLoginClick(state.authAction)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                                ) {
                                    Text(state.buttonText)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.primary,
                            thickness = 2.dp,
                            modifier = Modifier.weight(0.75f)
                        )

                        if (state.hasError) {
                            Text(
                                text = state.errorMessage,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }

                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.primary,
                            thickness = 2.dp,
                            modifier = Modifier.weight(0.75f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                }
            } else {
                LaunchedEffect(true) {
                    if (state.isLogged) {
                        navController.navigateFromLogin(Home)
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
        val loginViewModel : AuthViewModel = viewModel(
            factory = AuthViewModel.provideFactory()
        )

        LoginRoute(
            viewModel = loginViewModel,
            navController = rememberNavController(),
        )


    }
}