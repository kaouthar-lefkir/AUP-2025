package com.example.ramy_customer


import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun SuccessScreen(
    onHomeClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "bubble animation")
    val orangeColor = Color(0xFFF3AF4A)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Main content column - now properly centered
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier.size(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.both),
                        contentDescription = "Success",
                        modifier = Modifier.size(250.dp)
                    )
                }

                Text(
                    text = "Scan réussit",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1B5E20)
                )

                Text(
                    text = "Merci pour votre contribution!",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+2 points ajoutés à votre compte",
                            fontSize = 14.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }
            }

            // Bottom Navigation - now positioned at bottom
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter),
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
                                    .background(
                                        color = orangeColor,
                                        shape = RoundedCornerShape(8.dp)
                                    )
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
                        onClick = onHomeClick
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
                        onClick = onLeaderboardClick
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
                        onClick = { }
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
                        onClick = onProfileClick
                    )
                }
            }
        }
    }
}