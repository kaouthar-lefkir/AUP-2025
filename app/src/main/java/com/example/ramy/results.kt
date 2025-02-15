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

@Composable
fun Results(
    supermarketName: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val img = remember { mutableStateOf<Bitmap?>(null) }
    val orangeColor = Color(0xFFF3AF4A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar with curved bottom corners
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            color = orangeColor,
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.clickable { onBackClick() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = supermarketName,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // Port Analysis Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = "Resultat de l'analyse :",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Port Remy Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4A9586)
                    )
                ) {
                    Text(
                        text = "Port Remy",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }

                // Concurrent Port Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "Port concurrent",
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }

            Text(
                text = "Details :",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        // Bottom Navigation
        NavigationBar(
            modifier = Modifier
                .padding(horizontal = 4.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.lead),
                        contentDescription = "Leaderboard",
                        modifier = Modifier.size(24.dp)
                    )
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