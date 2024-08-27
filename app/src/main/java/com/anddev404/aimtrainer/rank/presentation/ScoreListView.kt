package com.anddev404.aimtrainer.rank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anddev404.aimtrainer.R
import com.anddev404.aimtrainer.rank.domain.Score
import com.anddev404.aimtrainer.ui.theme.RankNickTextColor
import com.anddev404.aimtrainer.ui.theme.RankPointsTextColor

@Composable
fun ScoreListView(modifier: Modifier = Modifier, scoreList: List<Score>) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background_main),
            contentDescription = "",
        )

        LazyColumn(Modifier.padding(8.dp)) {
            itemsIndexed(scoreList) { _, item ->
                ScoreRow(score = item)
                Spacer(modifier = Modifier.height(4.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreRow(score: Score = Score(0, "-", 0)) {

    Box {
        var backgroundHeight by remember { mutableStateOf(IntSize.Zero) }

        Row(modifier = Modifier.height(with(LocalDensity.current) { backgroundHeight.height.toDp() })) {
            Image(
                modifier = Modifier
                    .scale(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.rank_item_background_left),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.rank_item_background_center),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier
                    .scale(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.rank_item_background_right),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .onGloballyPositioned { backgroundHeight = it.size }
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            PositionItem(Modifier.padding(start = 4.dp, top = 2.dp, bottom = 2.dp), score.position)
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.offset(y = (-2).dp),
                    text = score.nick,
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = RankNickTextColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "${score.score}",
                    maxLines = 1,
                    fontSize = 24.sp,
                    color = RankPointsTextColor,
                    fontFamily = FontFamily(Font(R.font.font_default))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PositionItem(
    modifier: Modifier = Modifier
        .size(48.dp),
    position: Int = 1,
    textSize: TextUnit = 16.sp,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        var image = painterResource(id = R.drawable.rank_position_background)

        if (position == 1) {
            image = painterResource(id = R.drawable.rank_position_1)
        }
        if (position == 2) {
            image = painterResource(id = R.drawable.rank_position_2)
        }
        if (position == 3) {
            image = painterResource(id = R.drawable.rank_position_3)
        }

        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize(),
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Fit
        )
        Text(
            text = if (position > 3) "$position" else "",
            color = Color.White,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Black, offset = Offset(5f, 3f), blurRadius = 3f
                )
            ),
            fontSize = textSize,
            fontFamily = FontFamily(Font(R.font.font_default))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreListPreview() {
    ScoreListView(scoreList = Score.getFakeScore())
}