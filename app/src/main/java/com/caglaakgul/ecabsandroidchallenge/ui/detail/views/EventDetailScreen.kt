package com.caglaakgul.ecabsandroidchallenge.ui.detail.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caglaakgul.ecabsandroidchallenge.ui.detail.EventDetailViewModel
import com.caglaakgul.ecabsandroidchallenge.extension.formatDate
import com.caglaakgul.ecabsandroidchallenge.extension.splitStringBySlash

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel) {
    val event by viewModel.event.collectAsState()
    val repositoryUrl by viewModel.repositoryUrl.collectAsState()
    val (_, repoName) = event?.repo?.name?.splitStringBySlash() ?: Pair("", "")

    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight(),
                elevation = 6.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        model = event?.actor?.avatarUrl ?: "",
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(0.dp, 250.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Name: ${event?.actor?.login ?: ""}",
                        style = MaterialTheme.typography.h5,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Created Date: ${event?.createdAt?.formatDate() ?: ""}",
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Repository Name: $repoName",
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    WebViewButton(repositoryUrl)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
