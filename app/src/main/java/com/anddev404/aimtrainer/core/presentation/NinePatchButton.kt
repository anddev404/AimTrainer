package com.anddev404.aimtrainer.core.presentation

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
import com.anddev404.aimtrainer.R
import com.anddev404.aimtrainer.ui.theme.DialogBoxTextBorderColor

@Preview(showBackground = true)
@Composable
fun NinePatchButton(
    modifier: Modifier = Modifier,
    text: String = "But", fontSize: TextUnit = 28.sp,
    backgroundNinePatch: Int = R.drawable.button_green,
    icon: Painter? = null, onClick: () -> Unit = {}
) {
    Box(
        Modifier.draw9Patch(LocalContext.current, backgroundNinePatch),
        contentAlignment = Alignment.Center,
    ) {

        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        painter = icon,
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

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

