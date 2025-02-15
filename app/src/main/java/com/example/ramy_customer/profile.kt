package com.example.ramy_customer



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.grid.*

@Composable
fun ProfileHome(
    onHomeClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val orangeColor = Color(0xFFF3AF4A)
    val lightGrayBg = Color(0xFFF5F5F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGrayBg)
    ) {
        // Header with orange background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)  // Reduced height
                .background(
                    color = orangeColor,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Mon Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        // Profile Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-50).dp),  // Reduced offset
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),  // Reduced padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)  // Smaller profile picture
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Mohammed Amine",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,  // Smaller text
                    color = Color.Black
                )

                Text(
                    text = "mohamed@gmail.com",
                    fontSize = 12.sp,  // Smaller text
                    color = Color.Black.copy(alpha = 0.7f)
                )

                Text(
                    text = "081221447684",
                    fontSize = 12.sp,  // Smaller text
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }
        }

        // Points Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-40).dp),  // Added offset to move up
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)  // Reduced padding
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.flame),
                            contentDescription = "Streak",
                            tint = orangeColor,
                            modifier = Modifier.size(20.dp)  // Smaller icon
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "0 Days",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp  // Smaller text
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "vous avez:",
                        fontSize = 12.sp,  // Smaller text
                        color = Color.Gray
                    )
                    Text(
                        text = "125 points",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp  // Smaller text
                    )
                }
            }
        }

        // Calendar Section with reduced height
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-30).dp),  // Added offset to move up
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Header with month and navigation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Previous",
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "JAN 2022",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next",
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Weekday headers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                        Text(
                            text = day,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Calendar grid with smaller days
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),  // Fixed height for calendar
                    horizontalArrangement = Arrangement.SpaceBetween,
                    userScrollEnabled = false
                ) {
                    items(42) { index ->
                        val day = index + 1
                        if (day <= 31) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(2.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    fontSize = 10.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            containerColor = Color.White
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = onHomeClick
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
                onClick = onLeaderboardClick
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { /* Handle notifications */ }
            )

            NavigationBarItem(
                icon = {
                    Box(
                        modifier = Modifier
                            .background(orangeColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                            Text(
                                "Profile",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                },
                selected = false,
                onClick = onProfileClick
            )
        }
    }
}