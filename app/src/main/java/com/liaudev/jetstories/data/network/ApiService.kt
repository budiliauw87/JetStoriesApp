package com.liaudev.jetstories.data.network

import com.liaudev.jetstories.data.network.response.*
import retrofit2.http.*

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

    @GET("/stories/{id}")
    suspend fun getDetail(
        @Path("id") id: String,
        @Header("Authorization") token: String,
    ): DetailResponse

    @POST("login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("register")
    suspend fun registerUser(
        @Body requestBody: RegisterRequest
    ): BaseResponse
}