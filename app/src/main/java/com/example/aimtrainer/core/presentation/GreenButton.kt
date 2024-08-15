package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.TextBorderColor

@Preview(showBackground = true)
@Composable
fun GreenButton(text: String = "Button", onClick: () -> Unit = {}) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.widthIn(200.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.button_green),
                contentDescription = "",
            )
            OutlinedText(
                text = text,
                fontSize = 28.sp,
                borderColor = TextBorderColor,
                borderWidth = 8f,
                fontFamily = FontFamily(
                    Font(R.font.font_default)
                )
            )
        }
    }
}

