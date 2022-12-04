package com.liaudev.jetstories.model


/**
 * Created by Budiliauw87 on 2022-12-04.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
data class Story(
    val photoUrl: String,
    val name: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val lon: Double,
    val lat: Double
)