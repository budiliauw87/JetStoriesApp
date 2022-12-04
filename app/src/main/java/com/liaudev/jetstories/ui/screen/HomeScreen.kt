package com.liaudev.jetstories.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.liaudev.jetstories.components.StoryItem
import com.liaudev.jetstories.model.Story
import kotlinx.coroutines.delay

/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun HomeScreen( modifier: Modifier) {
    var refreshing by remember { mutableStateOf(true) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }
    val list:ArrayList<Story> = ArrayList()
    for (i in 1..6) {
        val item = Story("https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/1.jpg",
            "name here $i",
            "2022-12-04 00:00:00",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt  $i",
            "id$i",0.0,0.0)
        list.add(item)
    }
    SwipeRefresh(
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = {
            refreshing = true
        },
    ) {
        LazyColumn {
            items(list){
                StoryItem(it,modifier)
            }
        }
    }


}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier)
}