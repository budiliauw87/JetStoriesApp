package com.liaudev.jetstories

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.liaudev.jetstories.di.ViewModelFactory
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel

/**
 * Created by Budiman on 30/11/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    private val viewModel by viewModels<StoryViewModel> {
        ViewModelFactory.getInstance(this)
    }
    var keepSplashOnScreen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen().apply {
                setKeepOnScreenCondition { keepSplashOnScreen }
                setOnExitAnimationListener { it.remove() }
            }
        }
        viewModel.getUser().observe(this) {
            if (it.islogin) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            }
            keepSplashOnScreen = false
            finish()
        }

    }

}