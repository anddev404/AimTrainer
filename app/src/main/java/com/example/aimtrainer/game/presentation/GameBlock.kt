package com.example.aimtrainer.game.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.aimtrainer.R
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun Block(
    isFilled: Boolean = false,
    onClick: (Boolean) -> Unit = {}
) {
    Image(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                onClick(isFilled)
            },
        bitmap = if (isFilled) getRandomImageBitmap() else ImageBitmap.imageResource(id = R.drawable.block_empty),
        contentDescription = ""
    )
}

@Composable
fun getRandomImageBitmap(): ImageBitmap {
    val randomNumber = Random.nextInt(7)
    return when (randomNumber) {
        0 -> ImageBitmap.imageResource(id = R.drawable.block_red)
        1 -> ImageBitmap.imageResource(id = R.drawable.block_green)
        2 -> ImageBitmap.imageResource(id = R.drawable.block_blue)
        3 -> ImageBitmap.imageResource(id = R.drawable.block_yellow)
        4 -> ImageBitmap.imageResource(id = R.drawable.block_cyan)
        5 -> ImageBitmap.imageResource(id = R.drawable.block_pink)
        else -> ImageBitmap.imageResource(id = R.drawable.block_purple)
    }
}

