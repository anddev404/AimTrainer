package com.example.aimtrainer.game.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
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
            MainBox() {
                Board(5, {
                    viewModel.setStartTime()
                }) {
                    if (it) viewModel.setEndTime() else viewModel.loseGame()
                }
            }

        }

        is GameState.Ended -> {
            MainBox() {
                Board(5, {
                    viewModel.setStartTime()
                }) {
                    if (it) viewModel.setEndTime() else viewModel.loseGame()
                }
                TwoButtonsDialog("${stringResource(id = R.string.you_score)} ${viewModel.getScore()}",
                    leftText = stringResource(id = R.string.try_again),
                    rightText = stringResource(id = R.string.home),
                    { viewModel.startGame() },
                    { navController.navigate(Screen.MainScreen) })
            }
        }

        is GameState.Lost -> {
            MainBox() {
                Board(5, {
                    viewModel.setStartTime()
                }) {
                    if (it) viewModel.setEndTime() else viewModel.loseGame()
                }
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

@Composable
private fun MainBox(content: @Composable () -> Unit = {}) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_game),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        var columnSize by remember { mutableStateOf(IntSize.Zero) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { columnSize = it.size },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                painter = painterResource(id = R.drawable.top_bar_game),
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )

            Box(
                Modifier
                    .fillMaxHeight()
                    .width(
                        calculateWidthInDp(
                            columnSize.height - with(LocalDensity.current) {
                                48.dp
                                    .toPx()
                                    .toInt()
                            },
                            columnSize.width,
                            LocalDensity.current
                        )
                    )
                    .padding(16.dp), contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

private fun calculateWidthInDp(height: Int, width: Int, density: Density): Dp {
    val height = with(density) {
        height.toDp()
    }
    val width = with(density) {
        width.toDp()
    }
    return if (height > width) width else height
}

@Preview(showBackground = true, device = "spec:width=600dp,height=300dp")
@Composable
private fun MainBoxPreviewLandscape() {
    MainBox {
        Board()
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBoxPreviewPortraint() {
    MainBox {
        Board()
    }
}