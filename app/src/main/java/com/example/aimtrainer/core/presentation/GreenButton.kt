package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.DialogBoxTextBorderColor

@Preview(showBackground = true)
@Composable
fun GreenButton(
    text: String = "Button", fontSize: TextUnit = 28.sp,
    painter: Painter? = null, onClick: () -> Unit = {}
) {
    Box(
        Modifier.draw9Patch(LocalContext.current, R.drawable.button_green),
        contentAlignment = Alignment.Center,
    ) {

        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                painter?.let {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        painter = painter,
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                OutlinedText(
                    text = text,
                    fontSize = fontSize,
                    borderColor = DialogBoxTextBorderColor,
                    borderWidth = 8f,
                    fontFamily = FontFamily(
                        Font(R.font.font_default)
                    )
                )
            }
        }
    }
}

