package com.example.aimtrainer.settings.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aimtrainer.R
import com.example.aimtrainer.core.presentation.NinePatchButton

@Composable
fun SettingsScreen() {
    val context = LocalContext.current

    Box(
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_main),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            NinePatchButton(text = stringResource(R.string.privacy_policy)) {
                openPrivacyPolicy(context)
            }
            Spacer(modifier = Modifier.height(24.dp))
            NinePatchButton(text = stringResource(R.string.contact)) {
                sendEmail(context)
            }
        }
    }
}

private fun openPrivacyPolicy(context: Context) {
    val url = "https://anddev404.github.io/AimTrainer/privacy_policy/Privacy_Policy.htm"

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

private fun sendEmail(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SEND)

    emailIntent.type = "text/plain"
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("anddev404@gmail.com"))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Aim Trainer")
    emailIntent.putExtra(Intent.EXTRA_TEXT, "")

    context.startActivity(Intent.createChooser(emailIntent, "Send mail..."))
}