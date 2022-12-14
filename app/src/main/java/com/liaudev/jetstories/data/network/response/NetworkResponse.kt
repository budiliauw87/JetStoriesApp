package com.liaudev.jetstories.data.network.response

import com.liaudev.jetstories.model.Story


/**
 * Created by Budiliauw87 on 2022-12-09.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
data class StoryResponse(
    val listStory: List<Story>,
    val error: Boolean?,
    val message: String?
)

data class LoginResponse(
    val error: Boolean?,
    val message: String?,
    val loginResult: LoginResult?,
)

data class LoginResult(
    val userId: String?,
    val name: String?,
    val token: String?
)

data class BaseResponse(
    val error: Boolean?,
    val message: String?
)