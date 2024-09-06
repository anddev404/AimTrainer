package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anddev404.aimtrainer.navigation.Screen
import com.anddev404.aimtrainer.ui.theme.DialogBoxMainTextColor

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val registrationInState by viewModel.isRegistered.collectAsState()

    LaunchedEffect(registrationInState) {
        registrationInState.user?.let {
            navController.navigate(Screen.RankScreen)
        }
        registrationInState.error?.let {
            Toast.makeText(context, "${registrationInState.error}", Toast.LENGTH_LONG).show()
        }
    }

    if (registrationInState.loggingInProgress) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = DialogBoxMainTextColor)
        }
    } else {
        Column {
            Text(text = "registration")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { viewModel.register() }) {
                Text(text = "REGISTER")
            }
        }
    }
}