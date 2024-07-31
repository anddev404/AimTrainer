package com.example.aimtrainer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aimtrainer.GameScreen
import com.example.aimtrainer.HomeScreen

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen
    ) {
        composable<Screen.MainScreen> {
            HomeScreen(navController = navController)
        }
        composable<Screen.GameScreen> {
            GameScreen()
        }
    }
}