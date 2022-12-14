package com.liaudev.jetstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liaudev.jetstories.di.Injector
import com.liaudev.jetstories.di.ViewModelFactory
import com.liaudev.jetstories.navigation.Screen
import com.liaudev.jetstories.ui.screen.FavoriteScreen
import com.liaudev.jetstories.ui.screen.LoginScreen
import com.liaudev.jetstories.ui.screen.RegisterScreen
import com.liaudev.jetstories.ui.theme.JetStoriesTheme
import com.liaudev.jetstories.ui.viewmodel.AuthViewModel

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
                JetAuthApp()
            }
        }
    }
}

@Composable
fun JetAuthApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(
            Injector.provideRepository(
                LocalContext.current
            )
        )
    )
) {
    Scaffold(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController,viewModel)

            }
            composable(Screen.Register.route) {
                RegisterScreen(navController,viewModel)
            }
        }
    }
}