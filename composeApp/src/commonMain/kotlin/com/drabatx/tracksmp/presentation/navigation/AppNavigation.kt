package com.drabatx.tracksmp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.drabatx.tracksmp.presentation.view.screens.DetailScreen
import com.drabatx.tracksmp.presentation.view.screens.LoginScreen
import com.drabatx.tracksmp.presentation.view.screens.TracksScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Login.route) {
        composable(route = AppScreens.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.TrackList.route) {
            TracksScreen(navController = navController)
        }

        composable(route = AppScreens.TrackDetail.route,
            arguments = listOf(navArgument("track_id") {
                type = NavType.IntType
            })
        ) {
            val trackId = it.arguments?.getInt("track_id") ?: 1
            DetailScreen(navController = navController, trackId = trackId)
        }
    }
}