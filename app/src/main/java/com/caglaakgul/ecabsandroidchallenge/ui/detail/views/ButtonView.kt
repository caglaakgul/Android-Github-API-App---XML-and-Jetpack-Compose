package com.caglaakgul.ecabsandroidchallenge.ui.detail.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Blue,
    contentColor: Color = Color.White,
    cornerRadius: Dp = 16.dp,
    elevation: Dp = 4.dp,
    padding: PaddingValues = PaddingValues(16.dp),
    paddingVertical: Dp = 16.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(padding),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = ButtonDefaults.elevation(defaultElevation = elevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = paddingVertical),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text)
        }
    }
}