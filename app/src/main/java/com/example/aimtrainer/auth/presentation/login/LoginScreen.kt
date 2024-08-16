package com.example.aimtrainer.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aimtrainer.R
import com.example.aimtrainer.auth.domain.model.ValidationResult
import com.example.aimtrainer.core.presentation.GreenButton
import com.example.aimtrainer.core.presentation.StyledTextField
import com.example.aimtrainer.navigation.Screen

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val loginState by viewModel.loginForm.collectAsState()
        val passwordState by viewModel.passwordForm.collectAsState()
        val loggedInState by viewModel.loggedIn.collectAsState()

        LaunchedEffect(loggedInState) {
            loggedInState.user?.let {
                navController.navigate(Screen.RankScreen)
            }
            loggedInState.error?.let {
                Toast.makeText(context, "${loggedInState.error}", Toast.LENGTH_LONG).show();
            }
        }

        if (loggedInState.loggingInProgress) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

        } else {
            Spacer(modifier = Modifier.height(24.dp))

            StyledTextField(
                value = loginState.textFieldValue,
                onValueChange = { viewModel.updateLoginField(it) },
                isError = isError(loginState.validationResult),
                supportingText = { ShowErrorMessage(validationResult = loginState.validationResult) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            StyledTextField(
                value = passwordState.textFieldValue,
                onValueChange = { viewModel.updatePasswordField(it) },
                isError = isError(passwordState.validationResult),
                supportingText = { ShowErrorMessage(validationResult = passwordState.validationResult) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            GreenButton(stringResource(id = R.string.login)) { viewModel.validateAndLogin() }
        }
    }
}


private fun isError(validationResult: ValidationResult?): Boolean {
    return (validationResult?.successful == false)
}

@Composable
private fun ShowErrorMessage(validationResult: ValidationResult?) {
    if ((validationResult?.successful == false)) Text(validationResult.errorMessage)
}