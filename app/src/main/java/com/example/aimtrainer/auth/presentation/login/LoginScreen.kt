package com.example.aimtrainer.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aimtrainer.R
import com.example.aimtrainer.auth.domain.model.ValidationResult
import com.example.aimtrainer.core.presentation.DoubleGradientBox
import com.example.aimtrainer.core.presentation.NinePatchButton
import com.example.aimtrainer.core.presentation.OutlinedText
import com.example.aimtrainer.core.presentation.StyledTextField
import com.example.aimtrainer.navigation.Screen
import com.example.aimtrainer.ui.theme.DialogBoxMainTextColor
import com.example.aimtrainer.ui.theme.DialogBoxTextColor
import com.example.aimtrainer.ui.theme.TabBorderColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
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
            Toast.makeText(context, "${loggedInState.error}", Toast.LENGTH_LONG).show()
        }
    }



    DoubleGradientBox(topContent = {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText(
                text = stringResource(id = R.string.login_or_sign_up),
                textColor = DialogBoxMainTextColor,
                borderColor = TabBorderColor,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.font_default))
            )
        }
    }, bottomContent = {
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (loggedInState.loggingInProgress) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = DialogBoxMainTextColor)
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.enter_email),
                        color = DialogBoxTextColor,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.font_default))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    StyledTextField(
                        value = loginState.textFieldValue,
                        onValueChange = { viewModel.updateLoginField(it) },
                        isError = isError(loginState.validationResult),
                        supportingText = { ShowErrorMessage(validationResult = loginState.validationResult) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.enter_password),
                        color = DialogBoxTextColor,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.font_default))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
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

                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = stringResource(id = R.string.forgot_password),
                        color = DialogBoxMainTextColor
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    NinePatchButton(stringResource(id = R.string.sign_in)) { viewModel.validateAndLogin() }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(48.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_google),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(24.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.icon_fb),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.create_account),
                        color = DialogBoxMainTextColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    })
}


private fun isError(validationResult: ValidationResult?): Boolean {
    return (validationResult?.successful == false)
}

@Composable
private fun ShowErrorMessage(validationResult: ValidationResult?) {
    if ((validationResult?.successful == false)) Text(validationResult.errorMessage)
}