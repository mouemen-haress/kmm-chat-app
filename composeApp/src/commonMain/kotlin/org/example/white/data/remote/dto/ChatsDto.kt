package org.example.white.data.remote.dto

import kotlinx.serialization.Serializable
import org.example.white.domain.model.Chat
import org.example.white.util.DateHelper

@Serializable
data class ChatsDto(
    val id: String,
    val type: ChatType,
    val members: List<String>,
    val name: String? = null,
    val lastMesage: String? = null,
    val createdAt: Long
) {

    fun toChat(chatsDto: ChatsDto): Chat {
        return Chat(
            id = chatsDto.id,
            name = chatsDto.name ?: "",
            lastMessage = lastMesage ?: "",
            time = DateHelper.formatTimestamp(chatsDto.createdAt)
        )
    }
}

@Serializable
enum class ChatType {
    SINGLLE,
    GROUP
}
