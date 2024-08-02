package com.example.aimtrainer.rank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.core.presentation.OutlinedText
import com.example.aimtrainer.R
import com.example.aimtrainer.ui.theme.RankBorderColor
import com.example.aimtrainer.ui.theme.RankTabTextColorActive
import com.example.aimtrainer.ui.theme.RankTabTextColorInactive

@Composable
fun ScoreTabView(modifier: Modifier = Modifier, size: (IntSize) -> Unit = {}) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        stringResource(id = R.string.day),
        stringResource(id = R.string.week),
        stringResource(id = R.string.all)
    )

    var (leftTabBackground, leftTabTextColor) = Pair(
        painterResource(id = R.drawable.tab_score_list_left), RankTabTextColorInactive
    )
    var (centralTabBackground, centralTabTextColor) = Pair(
        painterResource(id = R.drawable.tab_score_list_left), RankTabTextColorInactive
    )
    var (rightTabBackground, rightTabTextColor) = Pair(
        painterResource(id = R.drawable.tab_score_list_left), RankTabTextColorInactive
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { size(it.size) }
        .then(modifier)) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = {},
            divider = {},
            containerColor = Color.Transparent,
        ) {
            Tab(0, selectedTabIndex, tabs[0], leftTabTextColor, leftTabBackground) {
                selectedTabIndex = it
            }
            Tab(1, selectedTabIndex, tabs[1], centralTabTextColor, centralTabBackground) {
                selectedTabIndex = it
            }
            Tab(2, selectedTabIndex, tabs[2], rightTabTextColor, rightTabBackground) {
                selectedTabIndex = it
            }
        }

        leftTabBackground = painterResource(id = R.drawable.tab_score_list_left)
        centralTabBackground = painterResource(id = R.drawable.tab_score_list_central)
        rightTabBackground = painterResource(id = R.drawable.tab_score_list_right)
        leftTabTextColor = RankTabTextColorInactive
        centralTabTextColor = RankTabTextColorInactive
        rightTabTextColor = RankTabTextColorInactive

        when (selectedTabIndex) {

            0 -> {
                leftTabBackground = painterResource(id = R.drawable.tab_score_list_left_clicked)
                leftTabTextColor = RankTabTextColorActive
            }

            1 -> {
                centralTabBackground =
                    painterResource(id = R.drawable.tab_score_list_central_clicked)
                centralTabTextColor = RankTabTextColorActive
            }

            2 -> {
                rightTabBackground = painterResource(id = R.drawable.tab_score_list_right_clicked)
                rightTabTextColor = RankTabTextColorActive
            }
        }
    }
}

@Composable
private fun Tab(
    index: Int,
    selectedIndex: Int,
    text: String,
    textColor: Color,
    painter: Painter,
    onClick: (Int) -> Unit
) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painter,
            contentDescription = ""
        )
        Tab(text = {
            OutlinedText(
                text = text,
                textColor = textColor,
                borderColor = RankBorderColor,
                borderWidth = 14f,
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.font_default)
                )
            )
        }, selected = selectedIndex == index, onClick = { onClick(index) })
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreTebViewPreview() {
    ScoreTabView()
}