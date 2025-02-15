package com.example.ramy

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuccessScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "bubble animation")

    // Animation for bubbles floating effect
    val bubbleScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bubble scale"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {

                // Center check mark
                Image(
                    painter = painterResource(id = R.drawable.both),
                    contentDescription = "Success",
                    modifier = Modifier.size(250.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Scan réussit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1B5E20)
            )

            Text(
                text = "Merci pour votre contribution!",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Points counter
            Surface(
                modifier = Modifier.padding(top = 16.dp),
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
    }
}