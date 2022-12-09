package com.liaudev.jetstories.data

import com.liaudev.jetstories.data.network.ApiService

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class StoryRepository(private val apiService: ApiService) {
    companion object {
        private const val LOGIN = "Login"
        private const val SIGNUP = "SignUp"
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