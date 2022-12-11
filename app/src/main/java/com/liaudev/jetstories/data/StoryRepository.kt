package com.liaudev.jetstories.data

import com.liaudev.jetstories.data.network.ApiService

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class StoryRepository(private val apiService: ApiService) {
    suspend fun getStories(token: String,page: Int, size: Int) = apiService.getStories(token, page, size)
    companion object {
        @Volatile
        private var INSTANCE: StoryRepository? = null
        fun getInstance(
            apiService: ApiService
        ): StoryRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoryRepository(apiService)
            }.also { INSTANCE = it }
    }
}