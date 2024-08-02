package com.example.aimtrainer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aimtrainer.game.presentation.GameScreen
import com.example.aimtrainer.home.presentation.HomeScreen
import com.example.aimtrainer.rank.presentation.RankScreen

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
        composable<Screen.RankScreen> {
            RankScreen()
        }
    }
}