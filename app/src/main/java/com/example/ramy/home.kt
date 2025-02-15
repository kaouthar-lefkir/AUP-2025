package com.example.ramy

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Supermarket(
    val name: String,
    val location: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSupermarketClick: (String) -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val orangeColor = Color(0xFFF3AF4A)
    val lightGrayBg = Color(0xFFF2F2F2)

    // Sample data for supermarkets
    val supermarkets = listOf(
        Supermarket("Supperette Ardis", "Mohammadiat, Alger"),
        Supermarket("Supérette Uno", "Bab ezzouar, Alger"),
        Supermarket("supérette Carrefour", "Bab ezzouar, Alger"),
        Supermarket("supérette el baraka", "Oued Smar, Alger"),
        Supermarket("Superette orange", "El Harrach, Alger"),
        Supermarket("Superette Lider Market", "Rouiba, Alger"),
        Supermarket("Superette Shop and Go", "Djasr Kasentina, Alger")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGrayBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header with orange background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = orangeColor,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Bienvenue Rayane",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.3f))
                            .clickable { onProfileClick() }  // Added navigation to profile
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-20).dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    placeholder = { Text("Cherchez un point de vente...") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            // List of supermarkets
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(supermarkets) { supermarket ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable { onSupermarketClick(supermarket.name) }, // Added navigation
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = supermarket.name,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = supermarket.location,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }

                            IconButton(onClick = { /* Handle more options */ }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More options"
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Navigation
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                NavigationBar(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(64.dp),
                    containerColor = Color.White
                ) {
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .background(orangeColor, shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Home,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.White
                                    )
                                    Text("Home", color = Color.White)
                                }
                            }
                        },
                        selected = false,
                        onClick = { /* Already on home screen */ }
                    )

                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.lead),
                                contentDescription = "Leaderboard",
                                modifier = Modifier.size(24.dp),
                                contentScale = ContentScale.Fit
                            )
                        },
                        selected = false,
                        onClick = { onLeaderboardClick() }  // Added navigation to leaderboard
                    )

                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        },
                        selected = false,
                        onClick = { /* Handle notifications */ }
                    )

                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        },
                        selected = false,
                        onClick = { onProfileClick() }  // Added navigation to profile
                    )
                }
            }
        }
    }
}