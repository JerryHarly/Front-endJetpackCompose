package com.example.tugas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNav(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeScreen : BottomBarNav(
        route = "HomeScreen",
        title = "Beranda",
        icon = Icons.Default.Home
    )
    object Screen1 : BottomBarNav(
        route = "screen_1",
        title = "Game",
        icon = Icons.Default.SportsEsports
    )
    object Screen2 : BottomBarNav(
        route = "screen_2",
        title = "Streamer",
        icon = Icons.Default.Videocam
    )
    object Screen3 : BottomBarNav(
        route = "screen_3",
        title = "Profil",
        icon = Icons.Default.Person
    )

    companion object {
        fun fromRoute(route: String?): BottomBarNav {
            return when (route) {
                "screen_1" -> Screen1
                "screen_2" -> Screen2
                "screen_3" -> Screen3
                else -> HomeScreen
            }
        }
    }
}