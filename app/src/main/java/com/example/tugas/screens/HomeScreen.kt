package com.example.tugas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tugas.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Data class untuk item
data class GameItem(
    val title: String,
    val developer: String,
    val imageRes: Int,
    val rating: Float
)

@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableStateOf("Game") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo/Gambar
        Image(
            painter = painterResource(id = R.drawable.benner_game),
            contentDescription = "Logo Game",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(75.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Kategori
        Text(
            text = "Kategori",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Single Row Kategori dengan UI yang lebih baik
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryButton(
                text = "Game",
                icon = Icons.Default.SportsEsports,
                isSelected = selectedCategory == "Game",
                onClick = { selectedCategory = "Game" }
            )
            CategoryButton(
                text = "Streamer",
                icon = Icons.Default.Videocam,
                isSelected = selectedCategory == "Streamer",
                onClick = { selectedCategory = "Streamer" }
            )
            CategoryButton(
                text = "Top Up",
                icon = Icons.Default.ShoppingCart,
                isSelected = selectedCategory == "TopUp",
                onClick = { selectedCategory = "TopUp" }
            )
            CategoryButton(
                text = "Lainnya",
                icon = Icons.Default.MoreVert,
                isSelected = selectedCategory == "Lainnya",
                onClick = { selectedCategory = "Lainnya" }
            )
        }

        // Menampilkan konten berdasarkan kategori yang dipilih
        when (selectedCategory) {
            "Game" -> GameContent()
            "Streamer" -> StreamerContent()
            "TopUp" -> TopUpContent()
            "Lainnya" -> Text("Konten Lainnya")
        }
    }
}

@Composable
private fun CategoryButton(
    text: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GameContent() {
    val gameList = listOf(
        GameItem("Mobile Legends", "Moonton", R.drawable.benner_game, 4.5f),
        GameItem("PUBG Mobile", "Tencent Games", R.drawable.benner_game, 4.3f),
        GameItem("Genshin Impact", "miHoYo", R.drawable.benner_game, 4.6f),
        GameItem("Free Fire", "Garena", R.drawable.benner_game, 4.2f),
        GameItem("Call of Duty Mobile", "Activision", R.drawable.benner_game, 4.7f),
        GameItem("Apex Legends Mobile", "EA", R.drawable.benner_game, 4.4f),
        GameItem("League of Legends: Wild Rift", "Riot Games", R.drawable.benner_game, 4.5f),
        GameItem("Valorant Mobile", "Riot Games", R.drawable.benner_game, 4.6f)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(gameList) { game ->
            GameCard(game)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StreamerContent() {
    val streamerList = listOf(
        StreamerItem("Jess No Limit", "Mobile Legends", "10M Followers", R.drawable.benner_game),
        StreamerItem("Windah Basudara", "Various Games", "8M Followers", R.drawable.benner_game),
        StreamerItem("Frost Diamond", "Minecraft", "5M Followers", R.drawable.benner_game),
        StreamerItem("Dyland PROS", "PUBG Mobile", "7M Followers", R.drawable.benner_game),
        StreamerItem("BTRS Zuxxy", "PUBG Mobile", "4M Followers", R.drawable.benner_game),
        StreamerItem("Bennymoza", "Mobile Legends", "3M Followers", R.drawable.benner_game),
        StreamerItem("Oura Gaming", "Free Fire", "6M Followers", R.drawable.benner_game)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(streamerList) { streamer ->
            StreamerCard(streamer)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class StreamerItem(
    val name: String,
    val mainGame: String,
    val followers: String,
    val imageRes: Int
)

@Composable
fun StreamerCard(streamer: StreamerItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = streamer.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = streamer.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = streamer.mainGame,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = streamer.followers,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun TopUpContent() {
    val topUpList = listOf(
        TopUpItem("Diamond Mobile Legends", "Mulai dari Rp 10.000", R.drawable.benner_game),
        TopUpItem("UC PUBG Mobile", "Mulai dari Rp 15.000", R.drawable.benner_game),
        TopUpItem("Genesis Crystal Genshin Impact", "Mulai dari Rp 16.000", R.drawable.benner_game),
        TopUpItem("Free Fire Diamonds", "Mulai dari Rp 5.000", R.drawable.benner_game),
        TopUpItem("Wild Cores Wild Rift", "Mulai dari Rp 20.000", R.drawable.benner_game),
        TopUpItem("Valorant Points", "Mulai dari Rp 50.000", R.drawable.benner_game),
        TopUpItem("Steam Wallet", "Mulai dari Rp 60.000", R.drawable.benner_game)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(topUpList) { item ->
            TopUpCard(item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class TopUpItem(
    val name: String,
    val price: String,
    val imageRes: Int
)

@Composable
fun TopUpCard(item: TopUpItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.price,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go to top up",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun GameCard(game: GameItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to detail */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = game.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = game.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Developer: ${game.developer}",
                style = MaterialTheme.typography.bodyMedium
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Rating: ${game.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
            }
        }
    }
}

