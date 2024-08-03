package com.example.aimtrainer.game.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aimtrainer.navigation.Screen

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = viewModel()) {

    val gameState by viewModel.state.collectAsState()

    when (gameState) {
        is GameState.Loading -> {}

        is GameState.Playing -> {
            Board(5, {
                viewModel.setStartTime()
            }) {
                if (it) viewModel.setEndTime() else viewModel.loseGame()
            }
        }

        is GameState.Ended -> {
            Column() {
                Text(text = "You score: ...")
                Button(onClick = { viewModel.startGame() }) {
                    Text(text = "Retry")
                }
                Button(onClick = { navController.navigate(Screen.MainScreen) }) {
                    Text(text = "Home")
                }
            }
        }

        is GameState.Lost -> {
            Column() {
                Text(text = "You lose: ...")
                Button(onClick = { viewModel.startGame() }) {
                    Text(text = "Try again")
                }
                Button(onClick = { navController.navigate(Screen.MainScreen) }) {
                    Text(text = "Home")
                }
            }
        }
    }
}