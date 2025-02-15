package com.example.ramy

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalInspectionMode

data class LeaderboardPlayer(
    val rank: Int,
    val username: String,
    val isOnline: Boolean
)

@Composable
fun Leaderboard(
    modifier: Modifier = Modifier,
    players: List<LeaderboardPlayer> = listOf(
        LeaderboardPlayer(1, "Mohamed1234", true),
        LeaderboardPlayer(2, "Ahmed Ahmed", true),
        LeaderboardPlayer(3, "kaouthar34", false),
        LeaderboardPlayer(4, "benmeur tarek", false),
        LeaderboardPlayer(5, "Rayane2003", false)
    )
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        players.forEach { player ->
            LeaderboardItem(player = player)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun LeaderboardItem(player: LeaderboardPlayer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank circle with orange background
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        Color(0xFFFFF3E0), // Light orange background
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "#${player.rank}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF3AF4A) // Orange text color
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Username
            Text(
                text = player.username,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Online/Offline status indicator
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        if (player.isOnline) Color(0xFF4CAF50) else Color(0xFFE57373),
                        RoundedCornerShape(6.dp)
                    )
            )
        }
    }
}

@Composable
fun Classement(
    onHomeClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val orangeColor = Color(0xFFF3AF4A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            color = orangeColor,
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Classement du jour",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Leaderboard
        Leaderboard(
            modifier = Modifier
                .weight(1f)
        )

        // Navigation Bar
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            containerColor = Color.White
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, "Home") },
                selected = false,
                onClick = { onHomeClick() }
            )
            NavigationBarItem(
                icon = {
                    Box(
                        modifier = Modifier
                            .background(orangeColor, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.lead),
                                contentDescription = "Leaderboard",
                                modifier = Modifier.size(20.dp)
                            )
                            Text("Classement", color = Color.White)
                        }
                    }
                },
                selected = false,
                onClick = { onLeaderboardClick() }
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
                icon = { Icon(Icons.Default.Person, "Profile") },
                selected = false,
                onClick = { onProfileClick() }
            )
        }
    }
}