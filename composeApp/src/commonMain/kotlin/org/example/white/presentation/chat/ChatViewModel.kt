package com.mouemen.azkary.presentation.chat

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mouemen.azkary.data.remote.ChatSocketService
import com.mouemen.azkary.data.remote.MessageService
import com.mouemen.azkary.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ChatViewModel(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService,
) : ScreenModel {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connectToChat(username: String) {
        getAllMessages()
        screenModelScope.launch {
            val result = chatSocketService.initSession(username)
            when (result) {
                is Resource.Success -> {
                    chatSocketService.observeMessage().onEach { message ->
                        val newList = state.value.messages.toMutableList().apply {
                            add(0, message)
                        }
                        _state.value = state.value.copy(
                            messages = newList
                        )
                    }.launchIn(screenModelScope)
                }

                is Resource.Error -> {
                    _toastEvent.emit(result.message ?: "Unknown error")
                }
            }
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun disconnect() {
        screenModelScope.launch {
            chatSocketService.closeConnection()
        }
    }

    fun getAllMessages() {
        screenModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = messageService.getAllMessages()
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage() {
        screenModelScope.launch {
            if (messageText.value.isNotBlank()) {
                chatSocketService.sendMessage(messageText.value)
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        disconnect()
    }
}