package com.example.aimtrainer

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aimtrainer.ui.theme.Tab_Bottom_Gradient
import com.example.aimtrainer.ui.theme.Tab_Top_Gradient

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
                    Tab_Top_Gradient,
                    Tab_Bottom_Gradient
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
        modifier = Modifier
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.divider),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )
}
