package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel()) {

    Column {
        Text(text = "registration")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.register() }) {
            Text(text = "REGISTER")
        }
    }

}