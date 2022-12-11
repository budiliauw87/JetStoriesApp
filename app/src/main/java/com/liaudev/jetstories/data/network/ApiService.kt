package com.liaudev.jetstories.data.network

import com.liaudev.jetstories.data.network.response.StoryResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
interface ApiService {
    @GET("stories")
    suspend fun getStories(
         @Header("Authorization") token: String,
         @Query("page") page: Int,
         @Query("size") size: Int
    ): StoryResponse
}