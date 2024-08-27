package com.anddev404.aimtrainer.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anddev404.aimtrainer.R
import com.anddev404.aimtrainer.navigation.Screen
import com.anddev404.aimtrainer.ui.theme.BestScoreBorderColor
import com.anddev404.aimtrainer.ui.theme.BestScoreTextColor1
import com.anddev404.aimtrainer.ui.theme.BestScoreTextColor2

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {

        Background()

        val configuration = LocalConfiguration.current
        val columns =
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            Modifier.align(Alignment.Center),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Logo()
                }
            }
            item {
                Column(
                    Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(48.dp))
                    ScoreView()

                    Spacer(modifier = Modifier.height(24.dp))
                    PlayButton {
                        navController.navigate(Screen.GameScreen)
                    }
                }
            }
        }
    }
}

@Composable
private fun Background() {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background_main),
            contentDescription = "",
        )
        Image(
            modifier = Modifier.fillMaxHeight(0.15f),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_leaves_left),
            contentDescription = "",
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_leaves_right),
            contentDescription = "",
        )
    }
}

@Composable
private fun Logo() {
    Image(
        modifier = Modifier
            .fillMaxHeight(0.55f)
            .fillMaxWidth()
            .scale(0.7f),
        painter = painterResource(id = R.drawable.logo),
        contentScale = ContentScale.Fit,
        contentDescription = "",
    )
}

@Composable
private fun ScoreView() {
    Box(contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .widthIn(250.dp)
                .border(
                    1.dp,
                    color = BestScoreBorderColor,
                    RoundedCornerShape(6.dp)
                )
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.background_best_score),
            contentDescription = "",
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.best_score),
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.font_default)
                ),
                color = BestScoreTextColor1
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "-",
                fontSize = 28.sp,
                fontFamily = FontFamily(
                    Font(R.font.font_default)
                ),
                color = BestScoreTextColor2
            )
        }
    }
}

@Composable
private fun PlayButton(onClick: () -> Unit) {
    var selected = remember { MutableInteractionSource() }
    val isPressed by selected.collectIsPressedAsState()
    var width = if (isPressed) 230.dp else 200.dp

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
        interactionSource = selected,
    ) {
        Box(
            modifier = Modifier.height(100.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.widthIn(width),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.button_play),
                contentDescription = "",
            )
            Text(
                text = stringResource(id = R.string.play), fontSize = 36.sp,
            )
        }
    }
}