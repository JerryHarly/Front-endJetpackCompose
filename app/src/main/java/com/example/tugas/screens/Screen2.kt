package com.example.tugas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tugas.R

@Composable
fun Screen2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Daftar Streamer",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(streamerList) { streamer ->
                StreamerGridCard(streamer)
            }
        }
    }
}

data class StreamerData(
    val name: String,
    val imageRes: Int,
    val followers: String,
    val mainGame: String,
    val rating: Float
)

private val streamerList = listOf(
    StreamerData(
        name = "Jess No Limit",
        imageRes = R.drawable.benner_game,
        followers = "10M Followers",
        mainGame = "Mobile Legends",
        rating = 4.8f
    ),
    StreamerData(
        name = "Windah Basudara",
        imageRes = R.drawable.benner_game,
        followers = "8M Followers",
        mainGame = "GTA V",
        rating = 4.7f
    ),
    StreamerData(
        name = "Frost Diamond",
        imageRes = R.drawable.benner_game,
        followers = "5M Followers",
        mainGame = "Minecraft",
        rating = 4.6f
    ),
    StreamerData(
        name = "Dyland PROS",
        imageRes = R.drawable.benner_game,
        followers = "7M Followers",
        mainGame = "PUBG Mobile",
        rating = 4.7f
    ),
    StreamerData(
        name = "BTRS Zuxxy",
        imageRes = R.drawable.benner_game,
        followers = "4M Followers",
        mainGame = "PUBG Mobile",
        rating = 4.5f
    ),
    StreamerData(
        name = "Bennymoza",
        imageRes = R.drawable.benner_game,
        followers = "3M Followers",
        mainGame = "Mobile Legends",
        rating = 4.4f
    ),
    StreamerData(
        name = "Oura Gaming",
        imageRes = R.drawable.benner_game,
        followers = "6M Followers",
        mainGame = "Free Fire",
        rating = 4.6f
    ),
    StreamerData(
        name = "Bang Alex",
        imageRes = R.drawable.benner_game,
        followers = "4.5M Followers",
        mainGame = "Mobile Legends",
        rating = 4.5f
    )
)

@Composable
fun StreamerGridCard(streamer: StreamerData) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Gambar Streamer
            Image(
                painter = painterResource(id = streamer.imageRes),
                contentDescription = streamer.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            // Informasi Streamer
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = streamer.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = streamer.mainGame,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${streamer.rating}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    // Followers
                    Text(
                        text = streamer.followers,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}