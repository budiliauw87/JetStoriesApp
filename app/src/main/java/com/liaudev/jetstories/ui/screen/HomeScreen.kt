package com.liaudev.jetstories.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.liaudev.jetstories.components.StoryItem
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel

/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: StoryViewModel,
) {
    val lazyPagingItems = viewModel.storiesPager.collectAsLazyPagingItems()
    val state: LazyListState = rememberLazyListState()

    SwipeRefresh(
        state = rememberSwipeRefreshState((lazyPagingItems.loadState.refresh is LoadState.Loading && lazyPagingItems.itemCount > 0)),
        onRefresh = {
            lazyPagingItems.refresh() },
    ) {
        LazyColumn(
            state = state,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            items(items = lazyPagingItems) { item ->
                item?.let {
                    StoryItem(it, modifier)
                }
            }
            if (lazyPagingItems.loadState.append is LoadState.Loading) {
                //load status of the next page
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                    }
                }
            }
        }
        if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
            if (lazyPagingItems.itemCount == 0) {//loading status when the first response page is loaded
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                }
            }
        } else if (lazyPagingItems.loadState.refresh is LoadState.Error) {
            //Load failed error page
            Box(modifier = Modifier.fillMaxSize()) {
                Button(modifier = Modifier.align(alignment = Alignment.Center),
                    onClick = { lazyPagingItems.refresh() }) {
                    Text(text = "Loading failed! Please try again")
                }
            }
        }
    }
}
