package org.example.white.presentation.halaqat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.datetime.Clock
import org.example.white.presentation.onBoarding.OnBoardingScreen
import org.example.white.util.DateHelper
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import whitelabel.composeapp.generated.resources.Res
import whitelabel.composeapp.generated.resources.halaqat_arabic_language_flag
import whitelabel.composeapp.generated.resources.halaqat_background
import whitelabel.composeapp.generated.resources.halaqat_english_language_flag
import whitelabel.composeapp.generated.resources.halaqat_logo

class Halaqat() : Screen {

    @Composable
    override fun Content() {
        var selectedLanguage by remember { mutableStateOf("ar") }
        var secretClickCount by remember { mutableStateOf(0) }
        var lastClickTime by remember { mutableStateOf(0L) }
        val navigator = LocalNavigator.current

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(Res.drawable.halaqat_background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Logo Image
                Image(
                    painter = painterResource(Res.drawable.halaqat_logo), // Replace with your logo resource
                    contentDescription = "App Logo",
                    modifier = Modifier.size(200.dp).clickable{
                        val currentTime = Clock.System.now().epochSeconds
                        if (currentTime - lastClickTime < 200) {
                            secretClickCount++
                        } else {
                            secretClickCount = 1
                        }
                        lastClickTime = currentTime

                        if (secretClickCount >= 5) {
                            secretClickCount = 0
                            navigator?.push(OnBoardingScreen())
                        }
                    },
                    contentScale = ContentScale.Fit
                )

                // Title
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "اختر لغتك",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = "Choose Your Language",
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                }

                // Language Options
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LanguageOption(
                        isSelected = selectedLanguage == "ar",
                        text = "العربية",
                        flagRes = Res.drawable.halaqat_arabic_language_flag,  // Replace with your flag image resource
                        onClick = { selectedLanguage = "ar" }
                    )
                    LanguageOption(
                        isSelected = selectedLanguage == "en",
                        text = "English",
                        flagRes = Res.drawable.halaqat_english_language_flag,  // Replace with your flag image resource
                        onClick = { selectedLanguage = "en" }
                    )
                }

                // Continue Button
                Button(
                    onClick = { /* Handle continue */ },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "تابع", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }

    @Composable
    fun LanguageOption(
        isSelected: Boolean,
        text: String,
        flagRes: DrawableResource,
        onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)
                .clickable { onClick() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Radio Button
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color(0xFF4CAF50) else Color.LightGray)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Text and Flag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text, fontSize = 18.sp)
                Image(
                    painter = painterResource(flagRes),
                    contentDescription = "Flag",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

}