package com.liaudev.jetstories.data.network.response


/**
 * Created by Budiliauw87 on 2022-12-12.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

data class LoginRequest(
    val email: String,
    val password: String,
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)