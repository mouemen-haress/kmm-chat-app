package com.mouemen.azkary.presentation.ussername

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.mouemen.azkary.presentation.chat.ChatViewModel
import kotlinx.coroutines.flow.collectLatest
import org.example.white.presentation.chat.ChatScreen

class UsernameScreen() : Screen {
    @Composable
    override fun Content() {
        var viewModel = koinScreenModel<UsernameViewModel>()
        val navigator = LocalNavigator.current

        LaunchedEffect(key1 = true) {
            viewModel.onJoinChat.collectLatest { username ->
                navigator?.push(ChatScreen(username))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                TextField(
                    value = viewModel.usernameText.value,
                    onValueChange = viewModel::onUsernameChange,
                    placeholder = {
                        Text(text = "Enter a username...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = viewModel::onJoinClick) {
                    Text(text = "Join")
                }
            }
        }
    }
}