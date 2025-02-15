package com.example.ramy_customer


import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun CameraPage(
    supermarketName: String,
    onImageCaptured: () -> Unit,
    onHomeClick: () -> Unit = {},
    onLeaderboardClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val img = remember { mutableStateOf<Bitmap?>(null) }
    val orangeColor = Color(0xFFF3AF4A)

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) {
        img.value = it
        if (it != null) {
            onImageCaptured()
        }
    }

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
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 24.dp,
                bottomEnd = 24.dp
            )
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

        // Scanner text
        Text(
            text = "Scanner le rayon",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)
        )

        // Camera Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .clickable { launcher.launch() },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (img.value != null) {
                        Image(
                            bitmap = img.value!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "Camera Icon",
                            modifier = Modifier.size(48.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation inside a Card
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
                                .background(Color(0xFFF3AF4A), shape = RoundedCornerShape(8.dp))
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
                    label = null,
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
                    label = null,
                    selected = false,
                    onClick = onLeaderboardClick
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    },
                    label = null,
                    selected = false,
                    onClick = onNotificationsClick
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    },
                    label = null,
                    selected = false,
                    onClick = onProfileClick
                )
            }
        }
    }
}