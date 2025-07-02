package org.example.white.presentation.chat

import org.example.white.domain.model.Chat


data class ChatListState(
    val isLoading: Boolean = false,
    val chatsList: ArrayList<Chat> = arrayListOf()
)