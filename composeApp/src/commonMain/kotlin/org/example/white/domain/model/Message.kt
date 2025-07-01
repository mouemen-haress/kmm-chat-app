package org.example.white.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val chatId: String? = null,
    val senderId: String? = null,
    val text: String? = null,
    val type: MessageType = MessageType.TEXT,
    val createdAt: Long? = null,
    val id: String? = null

)

@Serializable
enum class MessageType {
    TEXT,
    MEDIA
}