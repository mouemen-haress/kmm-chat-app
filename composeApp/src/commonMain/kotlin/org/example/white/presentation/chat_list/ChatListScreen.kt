package org.example.white.presentation.onBoarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.white.presentation.chat_list.ChatListViewModel
import org.example.white.presentation.chat.ChatItem
import org.example.white.presentation.chat.ChatScreen
import org.example.white.ui.theme.AppTheme

class ChatListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ChatListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            viewModel.fetchMyChatsList()
        }

        Box(modifier = Modifier.fillMaxSize()) {

            // LazyColumn always at the top
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.chatsList) { chat ->
                    ChatItem(chat) {
                        navigator?.push(ChatScreen(chat.id))
                    }
                }
            }

            // Centered Loader over the screen
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = AppTheme.colorScheme.third
                    )
                }
            }
        }
    }
}
