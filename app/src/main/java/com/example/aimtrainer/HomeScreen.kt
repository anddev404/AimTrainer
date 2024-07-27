package com.example.aimtrainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background_main),
            contentDescription = "",
        )
        Image(
            modifier = Modifier.fillMaxHeight(0.15f),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_leaves_left),
            contentDescription = "",
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_leaves_right),
            contentDescription = "",
        )
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.55f)
                    .scale(0.7f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
            )
        }
    }
}