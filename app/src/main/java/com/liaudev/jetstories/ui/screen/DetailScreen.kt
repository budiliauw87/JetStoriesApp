package com.liaudev.jetstories.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.liaudev.jetstories.model.Story

/**
 * Created by Budiman on 13/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
@Composable
fun DetailScreen(
    storyId: String
) {
    val story: Story = Gson().fromJson(storyId, Story::class.java)
    val initialName = story.name?.firstOrNull()?.toString()?.uppercase() ?: "?"
    Card(
        elevation = 5.dp,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        val photoName = story.photoUrl
        val urlPhoto = "https://story-api.dicoding.dev/images/stories/$photoName"
        Log.e("Detail", urlPhoto)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = initialName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = MaterialTheme.colors.primary, CircleShape)
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = story.name, style = MaterialTheme.typography.body1, maxLines = 1)
                    Text(
                        text = story.createdAt,
                        maxLines = 1,
                        style = MaterialTheme.typography.caption
                    )
                }
            }

            AsyncImage(
                model = urlPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .padding(top = 16.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = story.description
            )


        }
    }
}