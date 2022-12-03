package com.liaudev.jetstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liaudev.jetstories.components.BottomBar
import com.liaudev.jetstories.navigation.Screen
import com.liaudev.jetstories.ui.screen.FavoriteScreen
import com.liaudev.jetstories.ui.screen.HomeScreen
import com.liaudev.jetstories.ui.theme.JetStoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetStoriesTheme {
                JetStoriesApp()
            }
        }
    }
}

@Composable
fun JetStoriesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen("Home Screen",modifier)
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen("Favorite Screen")
            }
            composable(Screen.About.route) {
                FavoriteScreen("About Screen")
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetStoriesAppPreview() {
    JetStoriesTheme {
        JetStoriesApp()
    }
}

