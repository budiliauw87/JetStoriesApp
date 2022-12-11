package com.liaudev.jetstories.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.liaudev.jetstories.components.StoryItem
import com.liaudev.jetstories.di.Injector
import com.liaudev.jetstories.di.ViewModelFactory
import com.liaudev.jetstories.model.Story
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel
import kotlinx.coroutines.delay

/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: StoryViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository())
    )
) {
    var refreshing by remember { mutableStateOf(true) }
    val itemList = remember { mutableStateListOf<Story>() }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            if(itemList.size > 0){
                itemList.clear()
            }
            delay(2000)
            refreshing = false
        }
    }
    addDummy(itemList)
    SwipeRefresh(
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = {
            refreshing = true
        },
    ) {
        if(refreshing){
            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .background( color = MaterialTheme.colors.background )
            )
        }else{
            LazyColumn {
                items(itemList) {
                    StoryItem(it, modifier)
                }
            }
        }
        
    }


}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier)
}

fun addDummy(itemList: SnapshotStateList<Story>) {
    for (i in 1..6) {
        val item = Story(
            "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/1.jpg",
            "name here $i",
            "2022-12-04 00:00:00",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt  $i",
            "id$i", 0.0, 0.0
        )
        itemList.add(item)
    }
}