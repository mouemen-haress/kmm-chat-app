package org.example.white

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.mouemen.azkary.presentation.ussername.UsernameScreen
import org.example.white.di.initializeKoin
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    //    val colors = if (!isSystemInDarkTheme()) LightColors else DarkColors
    initializeKoin()

    MaterialTheme {
        Navigator(UsernameScreen())
    }
}