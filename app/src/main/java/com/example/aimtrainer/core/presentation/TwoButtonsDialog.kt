package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.DialogBoxGradientBottom
import com.example.aimtrainer.ui.theme.DialogBoxGradientTop


@Composable
fun TwoButtonsDialog(
    mainText: String = "",
    leftText: String = "",
    rightText: String = "",
    onLeftButtonClick: () -> Unit = {},
    onRightButtonClick: () -> Unit = {}
) {

    Dialog(onDismissRequest = { }) {
        Box {
            Column {
                Column(
                    modifier = Modifier.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                DialogBoxGradientTop, DialogBoxGradientBottom
                            )
                        )
                    ),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.divider_dialog_top),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.height(36.dp))
                    OutlinedText(
                        text = mainText,
                        fontFamily = FontFamily(Font(R.font.font_default)),
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(36.dp))

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.divider_dialog_bottom),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    NinePatchButton(
                        modifier = Modifier.widthIn(140.dp),
                        text = leftText,
                        fontSize = 24.sp,
                        backgroundNinePatch = R.drawable.button_blue
                    ) {
                        onLeftButtonClick()
                    }
                    NinePatchButton(
                        modifier = Modifier.widthIn(140.dp),
                        text = rightText,
                        fontSize = 24.sp
                    ) {
                        onRightButtonClick()
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .size(64.dp)
                    .offset(32.dp, (-24).dp),
                painter = painterResource(id = R.drawable.dialog_icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoButtonsDialogPreview() {
    TwoButtonsDialog("Are you sure to QUIT?", "Quit", "Resume")
}