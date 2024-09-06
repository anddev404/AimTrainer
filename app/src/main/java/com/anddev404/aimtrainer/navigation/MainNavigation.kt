package com.anddev404.aimtrainer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anddev404.aimtrainer.auth.presentation.login.LoginScreen
import com.anddev404.aimtrainer.auth.presentation.registration.presentation.RegistrationScreen
import com.anddev404.aimtrainer.game.presentation.GameScreen
import com.anddev404.aimtrainer.home.presentation.HomeScreen
import com.anddev404.aimtrainer.rank.presentation.RankScreen
import com.anddev404.aimtrainer.settings.presentation.SettingsScreen

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
            GameScreen(navController = navController)
        }
        composable<Screen.RankScreen> {
            RankScreen()
        }
        composable<Screen.LoginScreen> {
            LoginScreen(navController = navController)
        }
        composable<Screen.RegistrationScreen> {
            RegistrationScreen()
        }
        composable<Screen.SettingsScreen> {
            SettingsScreen()
        }
    }
}