package org.example.white

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import org.example.white.di.initializeKoin
import org.example.white.presentation.onBoarding.OnBoardingScreen
import org.example.white.ui.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    //    val colors = if (!isSystemInDarkTheme()) LightColors else DarkColors
    initializeKoin()

    Theme {
        Navigator(OnBoardingScreen())
    }
}