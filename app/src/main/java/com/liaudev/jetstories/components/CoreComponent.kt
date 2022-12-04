package com.liaudev.jetstories.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.liaudev.jetstories.R
import com.liaudev.jetstories.navigation.NavigationItem
import com.liaudev.jetstories.navigation.Screen


/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.Info,
                screen = Screen.About
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = true,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier, querySearch: MutableState<String>, ){
    val focusRequester = remember { FocusRequester() }
    var showClearButton =  remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = querySearch.value,
        onValueChange = {
            querySearch.value = it //update state

        },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .padding( vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(50)),

    )
}

