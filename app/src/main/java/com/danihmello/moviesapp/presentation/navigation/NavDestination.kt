package com.danihmello.moviesapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestination(val title: String, val route: String, val icon: ImageVector) {
    object Home : NavDestination(
        title = "Home",
        route = "home_screen",
        icon = Icons.Filled.Home
    )
    object Favorites : NavDestination(
        title = "Favorites",
        route = "favorites",
        icon = Icons.Filled.FavoriteBorder
    )
    object Account : NavDestination(
        title = "Account",
        route = "account",
        icon = Icons.Filled.AccountCircle
    )
}
