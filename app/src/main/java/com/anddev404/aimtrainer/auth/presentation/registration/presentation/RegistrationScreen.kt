package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anddev404.aimtrainer.R
import com.anddev404.aimtrainer.auth.domain.model.ValidationResult
import com.anddev404.aimtrainer.core.presentation.DoubleGradientBox
import com.anddev404.aimtrainer.core.presentation.NinePatchButton
import com.anddev404.aimtrainer.core.presentation.OutlinedText
import com.anddev404.aimtrainer.core.presentation.StyledTextField
import com.anddev404.aimtrainer.navigation.Screen
import com.anddev404.aimtrainer.ui.theme.DialogBoxMainTextColor
import com.anddev404.aimtrainer.ui.theme.DialogBoxTextColor
import com.anddev404.aimtrainer.ui.theme.TabBorderColor

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val registrationInState by viewModel.isRegistered.collectAsState()
    val emailState by viewModel.emailForm.collectAsState()
    val passwordState by viewModel.passwordForm.collectAsState()
    val nickState by viewModel.nickForm.collectAsState()


    LaunchedEffect(registrationInState) {
        registrationInState.user?.let {
            navController.navigate(Screen.RankScreen)
        }
        registrationInState.error?.let {
            Toast.makeText(context, "${registrationInState.error}", Toast.LENGTH_LONG).show()
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
                text = stringResource(id = R.string.sign_up),
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
            if (registrationInState.loggingInProgress) {
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
                        value = emailState.textFieldValue,
                        onValueChange = { viewModel.updateEmailField(it) },
                        isError = isError(emailState.validationResult),
                        supportingText = { ShowErrorMessage(validationResult = emailState.validationResult) },
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.enter_nickname),
                        color = DialogBoxTextColor,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.font_default))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    StyledTextField(
                        value = nickState.textFieldValue,
                        onValueChange = { viewModel.updateNickField(it) },
                        isError = isError(nickState.validationResult),
                        supportingText = { ShowErrorMessage(validationResult = nickState.validationResult) },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NinePatchButton(text = stringResource(id = R.string.sign_up)) { viewModel.validateAndRegister() }
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