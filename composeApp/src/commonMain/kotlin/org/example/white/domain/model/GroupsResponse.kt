package org.example.white.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: String,
    val type: ChatType,
    val members: List<String>,
    val name: String? = null,
    val createdAt: Long
) {

    fun toChat(chat: Chat): org.example.white.presentation.chat.Chat {

        return org.example.white.presentation.chat.Chat(
            _id = chat.id,
            name = chat.name ?: "",
            lastMessage = "",
            time = chat.createdAt.toString()
        )
    }
}

@Serializable
enum class ChatType {
    SINGLLE,
    GROUP
}
