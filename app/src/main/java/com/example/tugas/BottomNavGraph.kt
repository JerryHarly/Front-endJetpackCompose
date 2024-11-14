package com.example.tugas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugas.screens.*
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNav.HomeScreen.route
    ) {
        composable(route = BottomBarNav.HomeScreen.route) {
            HomeScreen()
        }
        
        composable(route = BottomBarNav.Screen1.route) {
            Screen1(navController)
        }

        composable(
            route = "detail/{title}/{image}/{rating}/{downloads}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("image") { type = NavType.IntType },
                navArgument("rating") { type = NavType.FloatType },
                navArgument("downloads") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                gameTitle = backStackEntry.arguments?.getString("title") ?: "",
                gameImage = backStackEntry.arguments?.getInt("image") ?: R.drawable.benner_game,
                gameRating = backStackEntry.arguments?.getFloat("rating") ?: 0f,
                gameDownloads = backStackEntry.arguments?.getString("downloads") ?: "",
                gameDescription = backStackEntry.arguments?.getString("description") ?: ""
            )
        }

        composable(route = BottomBarNav.Screen2.route) {
            Screen2()
        }

        composable(route = BottomBarNav.Screen3.route) {
            Screen3()
        }

        composable("search_screen") {
            SearchScreen(navController)
        }
    }
}

@Composable
fun SearchScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Halaman Pencarian")
    }
}