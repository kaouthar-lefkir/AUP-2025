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
                    .height(180.dp)
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
                    .offset(y = (-60).dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Mohammed Amine",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Text(
                        text = "mohamed@gmail.com",
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.7f)
                    )

                    Text(
                        text = "081221447684",
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height((-40).dp))

            // Points Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
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
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "0 Days",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "vous avez:",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "125 points",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // Calendar Section
            CalendarSection()

            Spacer(modifier = Modifier.weight(1f))

            // Updated Bottom Navigation
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
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
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
                        onClick = { /* Handle notifications */ }
                    )

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
                                        Icons.Default.Person,
                                        contentDescription = "Profile",
                                        modifier = Modifier.size(22.dp),
                                        tint = Color.White
                                    )
                                    Text("Profile", color = Color.White)
                                }
                            }
                        },
                        selected = false,
                        onClick = onProfileClick
                    )
                }
            }
        }
    }
}



@Composable
fun CalendarSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),  // Reduced vertical padding
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {  // Reduced padding
            // Header with month and navigation
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier
                        .size(20.dp)  // Smaller icons
                        .clickable { /* Handle previous month */ }
                )
                Text(
                    text = "JAN 2022",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp  // Smaller text
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier
                        .size(20.dp)  // Smaller icons
                        .clickable { /* Handle next month */ }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))  // Reduced spacing

            // Weekday headers
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN").forEach { day ->
                    Text(
                        text = day,
                        fontSize = 10.sp,  // Smaller text
                        color = Color.Gray,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))  // Reduced spacing

            // Calendar grid
            val daysInMonth = 31
            val firstDayOffset = 0 // Assuming month starts on Monday

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                userScrollEnabled = false  // Disable scrolling
            ) {
                items(42) { index ->
                    val day = index - firstDayOffset + 1
                    if (day in 1..daysInMonth) {
                        CalendarDay(
                            day = day,
                            hasYellowDot = day in listOf(2, 3, 11, 14, 16),
                            hasFire = day in 13..18 || day in 20..21
                        )
                    } else {
                        Spacer(modifier = Modifier.size(32.dp))  // Smaller spacing
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDay(
    day: Int,
    hasYellowDot: Boolean = false,
    hasFire: Boolean = false
) {
    Column(
        modifier = Modifier
            .padding(2.dp)  // Reduced padding
            .size(32.dp),   // Smaller size
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)  // Smaller size
                .then(
                    if (hasFire) Modifier.background(
                        color = Color(0xFFFFE4B5),
                        shape = CircleShape
                    ) else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.toString(),
                fontSize = 12.sp,  // Smaller text
                textAlign = TextAlign.Center
            )

            if (hasFire) {
                Icon(
                    painter = painterResource(id = R.drawable.flame),
                    contentDescription = "Fire",
                    modifier = Modifier
                        .size(12.dp)  // Smaller icon
                        .offset(x = 6.dp, y = (-6).dp),  // Adjusted offset
                    tint = Color(0xFFFF6B35)
                )
            }
        }

        if (hasYellowDot) {
            Box(
                modifier = Modifier
                    .size(4.dp)  // Smaller dot
                    .background(Color(0xFFFFD700), CircleShape)
            )
        }
    }
}

@Composable
fun BottomNavigation() {
    val orangeColor = Color(0xFFF3AF4A)

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
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                },
                label = null,
                selected = false,
                onClick = { /* Handle home navigation */ }
            )
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.lead),
                        contentDescription = "Phone Icon",
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                label = null,
                selected = false,
                onClick = { /* Handle store */ }
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
                label = null,
                selected = false,
                onClick = { /* Handle notifications */ }
            )
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
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(22.dp),
                                tint = Color.White
                            )
                            Text("Profile", color = Color.White)
                        }
                    }
                },
                label = null,
                selected = false,
                onClick = { /* Handle profile */ }
            )
        }
    }
}