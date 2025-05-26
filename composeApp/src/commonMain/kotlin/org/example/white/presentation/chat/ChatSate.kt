package org.example.white.presentation.chat

import org.example.white.domain.model.Message


data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)