package com.example.aimtrainer.rank.presentation

import androidx.compose.runtime.Composable
import com.example.aimtrainer.rank.domain.Score

@Composable
fun RankScreen() {
    ScoreListView(scoreList = Score.getFakeScore())
}