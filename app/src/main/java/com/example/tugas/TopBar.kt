package com.example.tugas

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,
    onSearchClick: (String) -> Unit = {},
    isSearchVisible: Boolean = true,
    isSearchActive: Boolean = false,
    onSearchActiveChange: (Boolean) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    // Data game untuk pencarian
    val items = remember {
        listOf(
            "Action Games",
            "Adventure Games",
            "RPG Games",
            "Strategy Games",
            "Sports Games",
            "Racing Games",
            "Puzzle Games",
            "Fighting Games",
            "Horror Games",
            "Simulation Games",
            "Mobile Games",
            "PC Games",
            "Console Games",
            "Game Reviews",
            "Game Guides",
            "Game News"
        )
    }

    // Filter items berdasarkan query
    val filteredItems = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            emptyList()
        } else {
            items.filter { it.contains(searchQuery, ignoreCase = true) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = {
                    if (isSearchActive) {
                        onSearchActiveChange(false)
                        searchQuery = ""
                    }
                }
            )
    ) {
        Column {
            TopAppBar(
                title = {
                    if (isSearchActive) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { newQuery ->
                                searchQuery = newQuery
                                onSearchClick(newQuery)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    enabled = true,
                                    onClick = { /* Mencegah click event */ }
                                ),
                            placeholder = { Text(text = "Cari...") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                            ),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onSearchActiveChange(false)
                                        searchQuery = ""
                                        onBackClick()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Tutup pencarian",
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                imeAction = ImeAction.Search
                            )
                        )
                    } else {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                navigationIcon = {
                    if (!isSearchActive) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Kembali",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                actions = {
                    if (isSearchVisible && !isSearchActive) {
                        IconButton(
                            onClick = {
                                onSearchActiveChange(true)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Cari",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )

            // Tampilkan hasil pencarian
            if (isSearchActive && searchQuery.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(
                            enabled = true,
                            onClick = { /* Mencegah click event */ }
                        )
                ) {
                    if (filteredItems.isEmpty()) {
                        item {
                            Text(
                                text = "Tidak ada hasil untuk: $searchQuery",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    } else {
                        items(filteredItems) { item ->
                            ListItem(
                                headlineContent = { Text(item) },
                                leadingContent = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                modifier = Modifier
                                    .clickable {
                                        // Handle item click
                                        onSearchClick(item)
                                        onSearchActiveChange(false)
                                        searchQuery = ""
                                    }
                                    .fillMaxWidth()
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

