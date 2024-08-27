package com.example.aimtrainer.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object MainScreen

    @Serializable
    data object GameScreen

    @Serializable
    data object RankScreen

    @Serializable
    data object LoginScreen

    @Serializable
    data object SettingsScreen
}