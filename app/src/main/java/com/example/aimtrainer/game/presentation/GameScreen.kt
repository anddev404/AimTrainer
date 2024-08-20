package com.example.aimtrainer.game.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aimtrainer.R
import com.example.aimtrainer.core.presentation.TwoButtonsDialog
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
            Box() {
                Board()

                TwoButtonsDialog("${stringResource(id = R.string.you_score)} ${viewModel.getScore()}",
                    leftText = stringResource(id = R.string.try_again),
                    rightText = stringResource(id = R.string.home),
                    { viewModel.startGame() },
                    { navController.navigate(Screen.MainScreen) })
            }
        }

        is GameState.Lost -> {
            Box {
                Board()

                TwoButtonsDialog(
                    stringResource(id = R.string.you_lose),
                    leftText = stringResource(id = R.string.try_again),
                    rightText = stringResource(id = R.string.home),
                    { viewModel.startGame() },
                    { navController.navigate(Screen.MainScreen) })
            }
        }
    }
}