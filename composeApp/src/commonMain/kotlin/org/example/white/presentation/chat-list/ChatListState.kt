package org.example.white.presentation.chat


data class ChatListState(
    val isLoading: Boolean = false,
    val chatsList: ArrayList<Chat> = arrayListOf()
)