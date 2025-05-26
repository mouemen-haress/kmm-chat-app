package org.example.white.data.remote.dto

import org.example.white.domain.model.Message
import kotlinx.serialization.Serializable
import org.example.white.util.DateHelper

@Serializable
data class MessageDto(
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String
) {
    fun toMessage(): Message {

        return Message(
            text = text,
            formatedTime = DateHelper.getCurrentFormattedTime(),
            username = username
        )
    }

}