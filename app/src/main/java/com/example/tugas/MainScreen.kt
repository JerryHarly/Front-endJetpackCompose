package com.example.tugas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // State untuk pencarian
    var isSearchActive by remember { mutableStateOf(false) }

    // Reset search state ketika route berubah
    LaunchedEffect(currentRoute) {
        isSearchActive = false
    }

    // Fungsi untuk mendapatkan judul berdasarkan route
    fun getScreenTitle(route: String?): String {
        return when (route) {
            BottomBarNav.HomeScreen.route -> "Beranda"
            BottomBarNav.Screen1.route -> "Game"
            BottomBarNav.Screen2.route -> "Streamer"
            BottomBarNav.Screen3.route -> "Profil"
            "search_screen" -> "Pencarian"
            else -> "Aplikasi Saya"
        }
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                shape = RoundedCornerShape(40.dp),
                shadowElevation = 1.dp
            ) {
                TopBar(
                    title = getScreenTitle(currentRoute),
                    onBackClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.navigateUp()
                        }
                    },
                    onSearchClick = { query ->
                        // Implementasi pencarian
                    },
                    isSearchVisible = currentRoute != "search_screen",
                    isSearchActive = isSearchActive,
                    onSearchActiveChange = { active ->
                        isSearchActive = active
                    }
                )
            }
        },
        bottomBar = { 
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(25.dp),
                shadowElevation = 1.dp
            ) {
                NavigationBar(
                    modifier = Modifier.height(70.dp),
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp
                ) {
                    BottomBar(navController = navController)
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BottomNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarNav.HomeScreen,
        BottomBarNav.Screen1,
        BottomBarNav.Screen2,
        BottomBarNav.Screen3,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { 
                    Icon(
                        screen.icon, 
                        contentDescription = screen.title,
                        tint = if (currentDestination?.route == screen.route) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    ) 
                },
                label = { 
                    Text(
                        text = screen.title,
                        color = if (currentDestination?.route == screen.route) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    ) 
                },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

