package com.liaudev.jetstories.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.liaudev.jetstories.data.local.AppPreferences
import com.liaudev.jetstories.data.network.ApiService
import com.liaudev.jetstories.data.network.response.LoginRequest
import com.liaudev.jetstories.data.network.response.LoginResponse
import com.liaudev.jetstories.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class StoryRepository(
    private val apiService: ApiService,
    private val pref: AppPreferences
) {
    suspend fun getStories(token: String, page: Int, size: Int) =
        apiService.getStories(token, page, size)

    fun getUserData(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

    suspend fun login(email: String, password: String): Flow<LoginResponse> {
        return flow {
            val response = apiService.loginUser(LoginRequest(email, password))
            emit(response)
        }

    }
    suspend fun saveUser(user: User) {
        pref.saveUser(user)
    }
    companion object {
        @Volatile
        private var INSTANCE: StoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: AppPreferences
        ): StoryRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoryRepository(apiService, pref)
            }.also { INSTANCE = it }
    }
}