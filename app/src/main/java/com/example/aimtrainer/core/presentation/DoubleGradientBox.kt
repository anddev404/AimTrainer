package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.DialogBoxGradientBottom
import com.example.aimtrainer.ui.theme.DialogBoxGradientCentral
import com.example.aimtrainer.ui.theme.DialogBoxGradientTop


@Preview(showBackground = true)
@Composable
fun DoubleGradientBox(
    modifier: Modifier = Modifier,
    topContent: @Composable () -> Unit = {},
    bottomContent: @Composable () -> Unit = {}
) {
    Column() {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(horizontalGradient)
                .padding(12.dp)
        ) {
            topContent()
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(verticalGradient)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.divider_horizontal),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .offset(y = (-14).dp)
            )
            bottomContent()
        }
    }
}

private val horizontalGradient = Brush.horizontalGradient(
    colors = listOf(
        DialogBoxGradientTop,
        DialogBoxGradientCentral,
        DialogBoxGradientTop
    )
)
private val verticalGradient = Brush.verticalGradient(
    colors = listOf(
        DialogBoxGradientTop,
        DialogBoxGradientBottom,
    )
)

