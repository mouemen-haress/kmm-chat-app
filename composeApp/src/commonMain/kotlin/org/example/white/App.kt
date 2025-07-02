package org.example.white

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import org.example.white.di.initializeKoin
import org.example.white.presentation.halaqat.Halaqat
import org.example.white.presentation.onBoarding.OnBoardingScreen
import org.example.white.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    initializeKoin()
    AppTheme {
        Scaffold() { padding ->
            Box(
                modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.primary)
            )
            {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(top = padding.calculateTopPadding()),
                ) {
                    Navigator(OnBoardingScreen())
                }
            }
        }
    }

}

