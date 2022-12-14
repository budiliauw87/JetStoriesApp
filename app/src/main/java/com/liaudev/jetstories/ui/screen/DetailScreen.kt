package com.liaudev.jetstories.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Created by Budiman on 13/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
@Composable
fun DetailScreen(
    storyId: String,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = storyId, Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}