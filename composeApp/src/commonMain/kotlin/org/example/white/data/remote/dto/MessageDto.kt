package org.example.white.data.remote.dto

import org.example.white.domain.model.Message
import kotlinx.serialization.Serializable
import org.example.white.util.DateHelper

@Serializable
data class MessageDto(
    val chatId: String,
    val senderId: String,
    val text: String,
    val type: MessageType = MessageType.TEXT,
    val createdAt: Long,
    val id: String
) {
    fun toMessage(): Message {

        return Message(
            text = text,
//            createdAt = DateHelper.getCurrentFormattedTime(),
            createdAt = 12333,
            senderId = senderId
        )
    }

}

@Serializable
enum class MessageType {
    TEXT,
    MEDIA
}