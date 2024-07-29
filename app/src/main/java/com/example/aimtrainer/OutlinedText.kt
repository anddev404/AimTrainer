package com.example.aimtrainer

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Preview(showBackground = true)
@Composable
fun OutlinedText(
    modifier: Modifier = Modifier,
    text: String = "",
    textColor: Color = Color.White,
    borderColor: Color = Color.Black,
    borderWidth: Float = 4f,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    fontFamily: FontFamily = MaterialTheme.typography.bodyMedium.fontFamily!!
) {
    Box(modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text, fontFamily = fontFamily, style = LocalTextStyle.current.merge(
                TextStyle(
                    color = borderColor,
                    fontSize = fontSize,
                    drawStyle = Stroke(width = borderWidth, join = StrokeJoin.Round)
                )
            )
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontFamily = fontFamily,
            color = textColor,
            fontSize = fontSize
        )
    }
}