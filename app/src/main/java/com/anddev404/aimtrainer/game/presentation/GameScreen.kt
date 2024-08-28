package com.anddev404.aimtrainer.game.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.anddev404.aimtrainer.R
import com.anddev404.aimtrainer.core.presentation.OutlinedText
import com.anddev404.aimtrainer.core.presentation.TwoButtonsDialog
import com.anddev404.aimtrainer.navigation.Screen
import com.anddev404.aimtrainer.ui.theme.TintColor

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
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Background()

        var columnSize by remember { mutableStateOf(IntSize.Zero) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { columnSize = it.size },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showNotAvailableYetToast(context) },
                    painter = painterResource(id = R.drawable.top_bar_game),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .scale(0.8f),
                        painter = painterResource(id = R.drawable.icon_score_cup),
                        contentDescription = "", colorFilter = ColorFilter.tint(TintColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedText(
                        modifier = Modifier
                            .align(Alignment.CenterVertically), text = "-", textColor = TintColor,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.font_default))
                    )
                }
            }


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

@Preview(showBackground = true)
@Composable
private fun Background() {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background_game),
            contentDescription = "",
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .align(Alignment.BottomStart),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_leaves_bottom_left),
            contentDescription = "",
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.img_leaves_bottom_right),
            contentDescription = "",
        )
    }
}

private fun calculateWidthInDp(height: Int, width: Int, density: Density): Dp {
    val heightDp = with(density) {
        height.toDp()
    }
    val widthDp = with(density) {
        width.toDp()
    }
    return if (heightDp > widthDp) widthDp else heightDp
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

private fun showNotAvailableYetToast(context: Context) {
    Toast.makeText(context, "Not available yet!", Toast.LENGTH_SHORT).show();
}