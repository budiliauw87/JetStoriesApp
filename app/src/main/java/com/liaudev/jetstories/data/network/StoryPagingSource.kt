package com.liaudev.jetstories.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.model.Story


/**
 * Created by Budiliauw87 on 2022-12-09.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class StoryPagingSource( private val repo: StoryRepository): PagingSource<Int, Story>(){

    override fun getRefreshKey(state: PagingState<Int, Story>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        TODO("Not yet implemented")
    }
}