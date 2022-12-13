package com.liaudev.jetstories.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.liaudev.jetstories.components.StoryItem
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel

/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: StoryViewModel,
) {
    val lazyPagingItems = viewModel.storiesPager.collectAsLazyPagingItems()
    val state: LazyListState = rememberLazyListState()
    val refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading

    val pullRefreshState = rememberPullRefreshState(refreshing, { lazyPagingItems.refresh() })
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(items = lazyPagingItems) { item ->
                item?.let {

                    StoryItem(it, modifier)
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                       Log.e("HomeScreen","State refresh")
                    }
                    loadState.append is LoadState.Loading -> { //loading when loadmore
                        Log.e("HomeScreen","loading item bottom")
                    }
                    loadState.refresh is LoadState.Error -> { // error when refresh
                        val e = lazyPagingItems.loadState.refresh as LoadState.Error
                        Log.e("HomeScreen",e.error.localizedMessage!!)
                    }

                    loadState.append is LoadState.Error -> { //error when load more
                        val e = lazyPagingItems.loadState.append as LoadState.Error
                        Log.e("HomeScreen",e.error.localizedMessage!!)
                    }
                }
            }

        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }


}
