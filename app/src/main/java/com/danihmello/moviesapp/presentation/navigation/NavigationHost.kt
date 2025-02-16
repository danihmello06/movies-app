package com.danihmello.moviesapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danihmello.moviesapp.presentation.account.AccountScreen
import com.danihmello.moviesapp.presentation.details.DetailsScreen
import com.danihmello.moviesapp.presentation.favorite.FavoriteScreen
import com.danihmello.moviesapp.presentation.home.HomeScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(NavDestination.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavDestination.Favorites.route) {
            FavoriteScreen(modifier = Modifier)
        }
        composable(NavDestination.Account.route) {
            AccountScreen(modifier = Modifier)
        }
        composable("details/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieID = backStackEntry.arguments?.getInt("movieId")
            movieID?.let {
                DetailsScreen(movieId = it)
            }
        }
    }
}