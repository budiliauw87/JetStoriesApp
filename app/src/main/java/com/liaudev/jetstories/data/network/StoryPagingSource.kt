package com.liaudev.jetstories.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liaudev.jetstories.data.local.AppPreferences
import com.liaudev.jetstories.model.Story
import kotlinx.coroutines.flow.first


/**
 * Created by Budiliauw87 on 2022-12-09.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class StoryPagingSource(
    private val apiService: ApiService,
    private val pref: AppPreferences
) : PagingSource<Int, Story>() {

    override fun getRefreshKey(state: PagingState<Int, Story>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val position = params.key ?: 1
            val token = pref.getUser().first().token
            if (token.isNotEmpty()) {
                val response = apiService.getStories(
                    "Bearer $token",
                    position,
                    params.loadSize
                )
                LoadResult.Page(
                    data = response.listStory,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (response.listStory.isEmpty()) null else position + 1
                )
            } else {
                return LoadResult.Error(Exception("Error"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}