package com.anddev404.aimtrainer.rank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anddev404.aimtrainer.R

@Composable
fun RankScreen(viewModel: RankViewModel = hiltViewModel()) {
    Column {
        var boxSize by remember { mutableStateOf(IntSize.Zero) }
        val boxWidth = with(LocalDensity.current) { boxSize.width.toDp() }
        val boxHeight = with(LocalDensity.current) { boxSize.height.toDp() }

        val bestScores by viewModel.bestScores.collectAsState()

        Box(
        ) {
            Image(
                modifier = Modifier.size(boxWidth, boxHeight),
                painter = painterResource(id = R.drawable.background_tab_score),
                contentScale = ContentScale.FillBounds,
                contentDescription = ""
            )
            ScoreTabView(
                Modifier
                    .padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 25.dp)
            ) {
                boxSize = it
            }
        }

        ScoreListView(
            scoreList = bestScores
        )
    }
}