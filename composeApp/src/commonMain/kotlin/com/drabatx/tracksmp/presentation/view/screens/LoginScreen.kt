package com.drabatx.tracksmp.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.drabatx.tracksmp.presentation.navigation.AppScreens
import com.drabatx.tracksmp.presentation.view.dialogs.MessageDialog
import com.drabatx.tracksmp.presentation.view.common.Screen
import com.drabatx.tracksmp.presentation.view.common.margin_medium
import com.drabatx.tracksmp.presentation.view.dialogs.LoadingDialog
import com.drabatx.tracksmp.presentation.view.viewmodels.LoginViewModel
import com.drabatx.tracksmp.utils.Result
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tracksmp.composeapp.generated.resources.Res
import tracksmp.composeapp.generated.resources.accept
import tracksmp.composeapp.generated.resources.email
import tracksmp.composeapp.generated.resources.error
import tracksmp.composeapp.generated.resources.error_email_incorrect
import tracksmp.composeapp.generated.resources.error_login
import tracksmp.composeapp.generated.resources.error_password_incorrect
import tracksmp.composeapp.generated.resources.label_login
import tracksmp.composeapp.generated.resources.password
import tracksmp.composeapp.generated.resources.show_password
import tracksmp.composeapp.generated.resources.title_error_email_incorrect
import tracksmp.composeapp.generated.resources.title_error_password_incorrect
import tracksmp.composeapp.generated.resources.welcome

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel= koinViewModel(), navController: NavController) {
    val isValidData by loginViewModel.isValidData.collectAsState()
    val loginState by loginViewModel.loginStateFlow.collectAsState()
    val hasNavigated = remember { mutableStateOf(false) }
    val isLogged by loginViewModel.isLoggedStateFlow.collectAsState(initial = false)
    LaunchedEffect(Unit) {
        loginViewModel.isLogged()
    }
    Screen {
        Scaffold { padding ->
            if (isLogged){
                GoToNextScreen(loginViewModel, hasNavigated, navController)
            }
            when (loginState){
                is Result.Loading -> {
                    LoadingDialog(true)
                }

                is Result.Error -> {
                    val message = ((loginState as Result.Error).exception.message)
                        ?: stringResource(Res.string.error_login)
                    MessageDialog(
                        title = stringResource(Res.string.error),
                        text = message,
                        showDialog = true,
                        onConfirm = { loginViewModel.resetForm() },
                        secondaryButtonText = stringResource(Res.string.accept)
                    )

                }
                is Result.Success -> {
                    GoToNextScreen(loginViewModel, hasNavigated, navController)
                }
                else -> {}
            }
            Column(
                modifier = Modifier.padding(
                    vertical = padding.calculateTopPadding(),
                    horizontal = margin_medium
                )
            ) {
                var email by remember { mutableStateOf("speedymovil") }
                var password by remember { mutableStateOf("speedymovil2024") }
                var passwordVisible by remember { mutableStateOf(false) }

                val icon = if (passwordVisible)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(Res.string.welcome),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            stringResource(Res.string.email),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            stringResource(Res.string.password),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(Res.string.show_password)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            loginViewModel.isValidData(email, password)
                        }
                    ),
                )

                Spacer(modifier = Modifier.height(height = margin_medium))

                Button(
                    onClick = {
                        loginViewModel.isValidData(emailAddress = email, password=password)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(
                            1f
                        )
                    )
                ) {
                    Text(stringResource(Res.string.label_login))
                }

                Spacer(Modifier.weight(1f))

                when (isValidData) {
                    LoginViewModel.LOGIN_STATE.ERROR_EMAIL -> {
                        MessageDialog(
                            title = stringResource(Res.string.title_error_email_incorrect),
                            text = stringResource(Res.string.error_email_incorrect),
                            primaryButtonText = stringResource(Res.string.accept),
                            onConfirm = { loginViewModel.resetForm() },
                            showDialog = true
                        )
                    }

                    LoginViewModel.LOGIN_STATE.ERROR_PASSWORD -> {
                        MessageDialog(
                            title = stringResource(Res.string.title_error_password_incorrect),
                            text = stringResource(Res.string.error_password_incorrect),
                            primaryButtonText = stringResource(Res.string.accept),
                            onConfirm = { loginViewModel.resetForm() },
                            showDialog = true
                        )
                    }

                    else -> {}
                }

            }
        }
    }
}

private fun GoToNextScreen(
    loginViewModel: LoginViewModel,
    hasNavigated: MutableState<Boolean>,
    navController: NavController
) {
    if (!hasNavigated.value) {
        hasNavigated.value = true
        loginViewModel.resetData()
        navController.navigate(AppScreens.TrackList.route) {
            popUpTo(AppScreens.Login.route) {
                inclusive = true
            }
        }
    }
}