package org.example.white.presentation.chat


data class Chat(
    val _id: String,
    val name: String,
    val lastMessage: String,
    val time: String // e.g., "10:30 AM"
)
