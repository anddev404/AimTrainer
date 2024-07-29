package com.example.aimtrainer

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.ui.theme.TabBorderColor
import com.example.aimtrainer.ui.theme.TabTextColorActive
import com.example.aimtrainer.ui.theme.TabTextColorInactive
import com.example.aimtrainer.ui.theme.Tab_Bottom_Gradient
import com.example.aimtrainer.ui.theme.Tab_Bottom_Gradient_Active
import com.example.aimtrainer.ui.theme.Tab_Top_Gradient
import com.example.aimtrainer.ui.theme.Tab_Top_Gradient_Active

@Preview(showBackground = true)
@Composable
fun BottomTabView() {
    val configuration = LocalConfiguration.current
    val arrangement =
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) Arrangement.SpaceAround else Arrangement.Center
    val fixedWidth = configuration.orientation != Configuration.ORIENTATION_PORTRAIT

    Column(
        Modifier.background(
            Brush.verticalGradient(
                listOf(
                    Tab_Top_Gradient, Tab_Bottom_Gradient
                )
            )
        )
    ) {
        Divider()

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = arrangement) {
            val modifier = if (fixedWidth) {
                Modifier.width(150.dp)
            } else {
                Modifier.weight(1f)
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Divider() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.divider),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )
}

@Preview(showBackground = true)
@Composable
private fun BottomTabViewItem(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    text: String = "",
    painter: Painter = painterResource(id = R.drawable.icon_settings),
    leftArrow: Boolean = false,
    rightArrow: Boolean = false,
) {

    val offset = if (isChecked) (-20).dp else 0.dp
    val topColor = if (isChecked) Tab_Top_Gradient_Active else Tab_Top_Gradient
    val bottomColor = if (isChecked) Tab_Bottom_Gradient_Active else Tab_Bottom_Gradient
    val buttonBackground =
        if (isChecked) painterResource(id = R.drawable.tab_button_filed) else painterResource(id = R.drawable.tab_button_empty)

    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .background(Brush.verticalGradient(listOf(topColor, bottomColor)))
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxHeight()
                .width(1.dp),
            painter = painterResource(id = R.drawable.divider_left_bottom_tab),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxHeight()
                .width(1.dp),
            painter = painterResource(id = R.drawable.divider_right_bottom_tab),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Box(
            Modifier
                .align(Alignment.Center)
                .height(72.dp)
                .offset(0.dp, offset), contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .aspectRatio(1f),
                painter = buttonBackground,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(14.dp)
                    .scale(0.7f)
                    .aspectRatio(1f),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .offset(0.dp, -(offset / 1.3f)),
            ) {
                if (leftArrow) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(16.dp),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.icon_left_arrow),
                        contentDescription = "",
                    )
                }

                val textColor = if (isChecked) TabTextColorActive else TabTextColorInactive
                OutlinedText(
                    Modifier.align(Alignment.Center),
                    text,
                    textColor,
                    TabBorderColor,
                    12f,
                    16.sp,
                    FontFamily(Font(R.font.font_default))
                )
                if (rightArrow) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(16.dp),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.icon_right_arrow),
                        contentDescription = "",
                    )
                }
            }
        }
    }
}
