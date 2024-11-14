package com.example.tugas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tugas.R
import androidx.navigation.NavHostController
import android.net.Uri
import androidx.compose.foundation.lazy.items

@Composable
fun Screen1(navController: NavHostController) {
    var activeCategory by remember { mutableStateOf("Semua") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Kategori Title
        Text(
            text = "Kategori Game",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            val categories = listOf("Semua", "Action", "Adventure", "Sports", "Racing", "Puzzle")
            items(categories) { category ->
                CategoryChip(
                    category = category,
                    isSelected = activeCategory == category,
                    onSelected = { activeCategory = category }
                )
            }
        }

        // Game List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filteredGames = if (activeCategory == "Semua") {
                categories.flatMap { it.games }
            } else {
                categories.find { it.name == activeCategory }?.games ?: emptyList()
            }
            
            items(filteredGames) { game ->
                GameListItem(game = game, navController = navController)
            }
        }
    }
}

data class Category(
    val name: String,
    val games: List<Game>
)

data class Game(
    val title: String,
    val imageRes: Int,
    val rating: Float,
    val downloads: String,
    val description: String
)

private val categories = listOf(
    Category(
        name = "Action",
        games = listOf(
            Game(
                "PUBG Mobile",
                R.drawable.benner_game,
                4.5f,
                "500M+",
                "Battle Royale game dengan 100 pemain bertarung untuk menjadi yang terakhir bertahan."
            ),
            Game(
                "Free Fire",
                R.drawable.benner_game,
                4.3f,
                "1B+",
                "Battle royale game dengan pertempuran intense 50 pemain dalam 10 menit."
            ),
            Game(
                "Call of Duty Mobile",
                R.drawable.benner_game,
                4.6f,
                "100M+",
                "Game FPS dengan mode multiplayer klasik dan battle royale."
            )
        )
    ),
    Category(
        name = "Adventure",
        games = listOf(
            Game(
                "Minecraft",
                R.drawable.benner_game,
                4.5f,
                "100M+",
                "Game petualangan sandbox dengan kreativitas tanpa batas."
            ),
            Game(
                "Sky: Children of Light",
                R.drawable.benner_game,
                4.8f,
                "50M+",
                "Petualangan sosial yang indah dengan visual memukau."
            )
        )
    ),
    Category(
        name = "Sports",
        games = listOf(
            Game(
                "FIFA Mobile",
                R.drawable.benner_game,
                4.2f,
                "100M+",
                "Game sepak bola dengan lisensi resmi dari FIFA."
            ),
            Game(
                "NBA 2K Mobile",
                R.drawable.benner_game,
                4.4f,
                "50M+",
                "Game basket dengan grafis realistis dan gameplay mendalam."
            )
        )
    ),
    Category(
        name = "Racing",
        games = listOf(
            Game(
                "Asphalt 9",
                R.drawable.benner_game,
                4.5f,
                "100M+",
                "Game balapan arcade dengan mobil super dan trek spektakuler."
            ),
            Game(
                "Real Racing 3",
                R.drawable.benner_game,
                4.3f,
                "100M+",
                "Simulasi balapan realistis dengan lisensi mobil asli."
            )
        )
    ),
    Category(
        name = "Puzzle",
        games = listOf(
            Game(
                "Candy Crush Saga",
                R.drawable.benner_game,
                4.4f,
                "1B+",
                "Game puzzle match-3 yang populer dan menghibur."
            ),
            Game(
                "Monument Valley",
                R.drawable.benner_game,
                4.8f,
                "50M+",
                "Puzzle game dengan arsitektur impossible yang indah."
            )
        )
    ),
    Category(
        name = "RPG",
        games = listOf(
            Game(
                "Genshin Impact",
                R.drawable.benner_game,
                4.6f,
                "50M+",
                "Open-world RPG dengan sistem gacha dan grafis memukau."
            ),
            Game(
                "Guardian Tales",
                R.drawable.benner_game,
                4.5f,
                "10M+",
                "RPG retro dengan puzzle dan cerita menarik."
            ),
            Game(
                "Epic Seven",
                R.drawable.benner_game,
                4.4f,
                "5M+",
                "RPG turn-based dengan animasi anime yang indah."
            )
        )
    ),
    Category(
        name = "Strategy",
        games = listOf(
            Game(
                "Mobile Legends",
                R.drawable.benner_game,
                4.7f,
                "1B+",
                "MOBA 5v5 dengan pertempuran tim yang strategis."
            ),
            Game(
                "Clash of Clans",
                R.drawable.benner_game,
                4.5f,
                "500M+",
                "Game strategi membangun base dan berperang."
            ),
            Game(
                "Rise of Kingdoms",
                R.drawable.benner_game,
                4.4f,
                "50M+",
                "Game strategi perang dengan setting sejarah dunia."
            )
        )
    )
)

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(onClick = onSelected),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(0xFF3B5BA9) 
               else Color(0xFFF3F4F6)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) Color.White
                   else Color(0xFF64748B)
        )
    }
}

@Composable
fun GameListItem(game: Game, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val encodedTitle = Uri.encode(game.title)
                val encodedDownloads = Uri.encode(game.downloads)
                val encodedDescription = Uri.encode(game.description)
                navController.navigate(
                    "detail/$encodedTitle/${game.imageRes}/${game.rating}/$encodedDownloads/$encodedDescription"
                )
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Game Image
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = game.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            // Game Info
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${game.rating} • ${game.downloads}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

