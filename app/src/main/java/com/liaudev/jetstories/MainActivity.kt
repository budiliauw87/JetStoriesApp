package com.liaudev.jetstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liaudev.jetstories.components.BottomBar
import com.liaudev.jetstories.navigation.Screen
import com.liaudev.jetstories.ui.screen.AboutScreen
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
    val stateTitle = remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stateTitle.value,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        },
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
                stateTitle.value = "Home"
                HomeScreen(modifier)
            }
            composable(Screen.Favorite.route) {
                stateTitle.value = "Favorite"
                FavoriteScreen(modifier)
            }
            composable(Screen.About.route) {
                stateTitle.value = "About"
                AboutScreen(modifier)
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

