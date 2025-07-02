package org.example.white.presentation.chat

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.white.data.remote.ChatSocketService
import org.example.white.data.remote.ApiService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.white.data.remote.dto.MessageDto
import org.example.white.domain.model.Message
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.util.Constants
import org.example.white.util.DateHelper

class ChatViewModel(
    private val apiService: ApiService,
    private val chatSocketService: ChatSocketService,
    private val preferencesRepository: PreferencesRepository
) : ScreenModel {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connectToChat(chatId: String) {
        getAllMessages(chatId)
        screenModelScope.launch {
            chatSocketService.observeMessage().onEach { message ->
                val newList = state.value.messages.toMutableList().apply {
                    add(0, message)
                }
                _state.value = state.value.copy(
                    messages = newList
                )
            }.launchIn(screenModelScope)
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

    fun getAllMessages(chatId: String) {
        screenModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = apiService.getAllMessages(chatId)
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage(chatId: String) {
        screenModelScope.launch(Dispatchers.IO) {
            val username = preferencesRepository.getValue(Constants.MY_LOGGED_IN_ID)
            if (!username.isNullOrEmpty()) {
                if (messageText.value.isNotBlank()) {

                    chatSocketService.sendMessage(
                        Json.encodeToString(
                            MessageDto(
                                chatId = chatId,
                                senderId = username,
                                text = messageText.value,
                                createdAt = DateHelper.getCurrentTime()
                            )
                        )
                    )
                    withContext(Dispatchers.Main){
                        onMessageChange("")
                    }
                }
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        disconnect()
    }
}