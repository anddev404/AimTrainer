package com.anddev404.aimtrainer.game.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun Board(howManyBlocks: Int = 5, gameStarted: () -> Unit = {}, gameEnded: (Boolean) -> Unit = {}) {
    Box {
        var generatedNumber by remember {
            mutableIntStateOf(Random.nextInt(100))
        }
        var counter = 0

        LazyVerticalGrid(columns = GridCells.Fixed(10)) {
            items(100) {

                Block(it == generatedNumber) { isAccurate ->
                    if (isAccurate) {
                        counter++
                        if (counter == 1) gameStarted()
                        if (counter == howManyBlocks) gameEnded(true)
                        generatedNumber = Random.nextInt(100)
                    } else {
                        gameEnded(false)
                    }
                }
            }
        }
    }
}