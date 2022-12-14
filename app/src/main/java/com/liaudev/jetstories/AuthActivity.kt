package com.liaudev.jetstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.liaudev.jetstories.ui.screen.LoginScreen
import com.liaudev.jetstories.ui.theme.JetStoriesTheme

/**
 * Created by Budiman on 12/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetStoriesTheme {
                LoginScreen()
            }
        }
    }
}