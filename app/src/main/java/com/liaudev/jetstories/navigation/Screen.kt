package com.liaudev.jetstories.navigation


/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailStory : Screen("detail/storyId={storyId}") {
        fun createRoute(storyId: String) = "detail/storyId=$storyId"
    }

    object Login : Screen("login")
    object Register : Screen("register")
}