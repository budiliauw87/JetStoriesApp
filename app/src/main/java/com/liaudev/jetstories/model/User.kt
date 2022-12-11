package com.liaudev.jetstories.model


/**
 * Created by Budiliauw87 on 2022-12-03.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
data class User(
    val userId: String,
    val name: String,
    val token: String,
    val islogin: Boolean
)