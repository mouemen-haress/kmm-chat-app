package com.mouemen.azkary.presentation.chat

import com.mouemen.azkary.domain.model.Message


data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)