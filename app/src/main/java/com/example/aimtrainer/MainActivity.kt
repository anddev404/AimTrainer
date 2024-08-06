package com.example.aimtrainer

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.aimtrainer.home.presentation.BottomTabEvent
import com.example.aimtrainer.home.presentation.BottomTabView
import com.example.aimtrainer.navigation.MainNavigation
import com.example.aimtrainer.navigation.Screen
import com.example.aimtrainer.ui.theme.AimTrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current

            AimTrainerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomTabView() {
                        when (it) {
                            BottomTabEvent.OnGameClick -> navController.navigate(Screen.MainScreen)
                            BottomTabEvent.OnLevelClick -> showAvailableSoonToast(context)
                            BottomTabEvent.OnRankClick -> navController.navigate(Screen.RankScreen)
                            BottomTabEvent.OnSettingClick -> showAvailableSoonToast(context)
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

private fun showAvailableSoonToast(context: Context) {
    Toast.makeText(
        context, "${context.getString(R.string.available_soon)}", Toast.LENGTH_LONG
    ).show();
}