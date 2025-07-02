package org.example.white.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import cafe.adriel.voyager.core.screen.Screen
import org.example.white.presentation.chat.ChatViewModel
import kotlinx.coroutines.flow.collectLatest
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.white.ToastDurationType
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.presentation.common.MessageItem
import org.example.white.showToastMsg
import org.example.white.util.Constants
import org.koin.mp.KoinPlatform.getKoin

class ChatScreen(
    val chatId: String
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ChatViewModel>()
        val selectedMessageIds = remember { mutableStateListOf<String>() }

        LaunchedEffect(key1 = true) {
            viewModel.toastEvent.collectLatest { message ->
                showToastMsg("This is Toast Message", ToastDurationType.LONG)
            }

        }
        val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    viewModel.connectToChat(chatId)
                } else if (event == Lifecycle.Event.ON_STOP) {
                    viewModel.disconnect()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
        val state = viewModel.state.value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true
            ) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                items(state.messages) { message ->
                    val isOwnMessage = message.isItMyMessage
                    val isSelected = selectedMessageIds.contains(message.id)

                    MessageItem(
                        isOwnMessage,
                        message.senderName,
                        message.text,
                        message.createdAt.toString(),
                        onToggleSelect = {
                            if (isSelected) selectedMessageIds.remove(message.id)
                            else message.id?.let {
                                selectedMessageIds.add(it)
                                println()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = viewModel.messageText.value,
                    onValueChange = viewModel::onMessageChange,
                    placeholder = {
                        Text(text = "Enter a message")
                    },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { viewModel.sendMessage(chatId) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            }
        }
    }
}