package com.liaudev.jetstories.navigation

import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
