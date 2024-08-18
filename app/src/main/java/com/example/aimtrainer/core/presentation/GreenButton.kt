package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.DialogBoxTextBorderColor

@Preview(showBackground = true)
@Composable
fun GreenButton(text: String = "Button", onClick: () -> Unit = {}) {

    Box(
        Modifier.draw9Patch(LocalContext.current, R.drawable.button_green),
        contentAlignment = Alignment.Center,
    ) {

        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
        ) {
            OutlinedText(
                text = text,
                fontSize = 28.sp,
                borderColor = DialogBoxTextBorderColor,
                borderWidth = 8f,
                fontFamily = FontFamily(
                    Font(R.font.font_default)
                )
            )
        }
    }
}

