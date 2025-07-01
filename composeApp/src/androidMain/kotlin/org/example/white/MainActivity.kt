package org.example.white

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.example.white.data.remote.ChatSocketService
import org.example.white.util.Resource
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }


}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}