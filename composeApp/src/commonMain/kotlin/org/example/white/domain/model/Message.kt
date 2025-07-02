package org.example.white.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val chatId: String? = null,
    val senderName: String? = null,
    val senderId: String,
    val text: String? = null,
    val type: MessageType = MessageType.TEXT,
    val createdAt: String? = null,
    val isItMyMessage: Boolean,
    val id: String? = null

)

@Serializable
enum class MessageType {
    TEXT,
    MEDIA
}