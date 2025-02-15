package com.example.ramy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SplashScreen(onGetStartedClick: () -> Unit = {}) {
    // Define colors
    val tealColor = Color(0xFF3F7E76)
    val buttonColor = Color.White
    val buttonTextColor = Color.Black
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()

    Box(modifier = Modifier.fillMaxSize()) {
        // Teal background for the entire screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(tealColor)
        )

        Image(
            painter = painterResource(id = R.drawable.footer), // Replace with your image resource
            contentDescription = "Bottom wave image",
            modifier = Modifier
                .fillMaxWidth()
                .height(750.dp) // Increased height for better coverage
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds // Ensures the image fills the entire space
        )

        // Content container
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ramy Logo at the top
            Image(
                painter = painterResource(id = R.drawable.logo_ramy),
                contentDescription = "Ramy Logo",
                modifier = Modifier
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    .size(80.dp)
                    .align(Alignment.End),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(1f))

            // Merchandeasy Logo and Text
            Image(
                painter = painterResource(id = R.drawable.logo_merchandeasy),
                contentDescription = "Phone Icon",
                modifier = Modifier.size(275.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(1f))

            // Commencer Button
            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .width(280.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Commencer",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = buttonTextColor,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}