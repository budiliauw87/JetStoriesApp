package com.liaudev.jetstories.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.liaudev.jetstories.R


/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun AboutScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AboutScreenPreview() {
    AboutScreen(modifier = Modifier)
}