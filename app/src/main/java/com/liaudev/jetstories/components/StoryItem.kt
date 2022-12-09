package com.liaudev.jetstories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.liaudev.jetstories.model.Story


/**
 * Created by Budiliauw87 on 2022-12-04.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

@Composable
fun StoryItem(item :Story,modifier:Modifier){
    Card(
        elevation = 5.dp,
        modifier = Modifier.padding(0.dp,0.dp,0.dp,8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {  }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "A",
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
                ){
                    Text(text = item.name,style = MaterialTheme.typography.body1, maxLines = 1)
                    Text(text = item.createdAt, maxLines = 1,style = MaterialTheme.typography.caption)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = item.description)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = item.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun StoryItemPreview() {
    val item = Story("","name here","2022-12-04 00:00:00","decreption","id",0.0,0.0)
    StoryItem(item, modifier = Modifier)
}