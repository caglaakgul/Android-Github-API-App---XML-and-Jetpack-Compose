package com.caglaakgul.ecabsandroidchallenge.ui.detail.views

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WebViewButton(url: String) {
    val openUrl = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ -> }

    if (url.isNotEmpty()) {
        ButtonView(
            text = "Go to Repository",
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                openUrl.launch(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.DarkGray,
            contentColor = Color.White,
            cornerRadius = 24.dp,
            elevation = 6.dp,
            padding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            paddingVertical = 8.dp
        )
    }
}
