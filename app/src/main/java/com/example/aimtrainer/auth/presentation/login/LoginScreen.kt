package com.example.aimtrainer.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aimtrainer.auth.domain.use_case.ValidationResult

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel = hiltViewModel()) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val loginState by viewModel.login.collectAsState()
        val passwordState by viewModel.password.collectAsState()

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = loginState.textFieldValue,
            onValueChange = { viewModel.changeLogin(it) },
            isError = isError(loginState.validationResult),
            supportingText = { ShowErrorMessage(validationResult = loginState.validationResult) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = passwordState.textFieldValue,
            onValueChange = { viewModel.changePassword(it) },
            isError = isError(passwordState.validationResult),
            supportingText = { ShowErrorMessage(validationResult = passwordState.validationResult) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.validateAndLogin() }) {
            Text(text = "Login")
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