package com.example.aimtrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.aimtrainer.navigation.MainNavigation
import com.example.aimtrainer.navigation.Screen
import com.example.aimtrainer.ui.theme.AimTrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            AimTrainerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomTabView() {
                            when (it) {
                                BottomTabEvent.OnGameClick -> TODO()
                                BottomTabEvent.OnLevelClick -> TODO()
                                BottomTabEvent.OnRankClick -> navController.navigate(Screen.RankScreen)
                                BottomTabEvent.OnSettingClick -> TODO()
                            }
                        }
                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavigation(navController)
                    }
                }
            }
        }
    }
}