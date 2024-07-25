package com.example.aimtrainer

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.aimtrainer.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Button(onClick = {
        navController.navigate(Screen.GameScreen)
    }) {
        Text(text = "Home Screen")
    }

}