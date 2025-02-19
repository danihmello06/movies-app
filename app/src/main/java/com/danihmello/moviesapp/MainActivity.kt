package com.danihmello.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.danihmello.moviesapp.presentation.navigation.NavDestination
import com.danihmello.moviesapp.presentation.navigation.NavigationHost
import com.danihmello.moviesapp.presentation.theme.MoviesAppTheme
import com.danihmello.moviesapp.presentation.theme.grayBackground
import com.danihmello.moviesapp.presentation.theme.lightGray
import com.danihmello.moviesapp.presentation.theme.mediumGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val navItemList = listOf(
        NavDestination.Home,
        NavDestination.Favorites,
        NavDestination.Account
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = grayBackground,
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.title
                            )
                        },
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = lightGray,
                            indicatorColor = mediumGray,
                            unselectedIconColor = mediumGray,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavigationHost(navController, innerPadding)
    }
}
