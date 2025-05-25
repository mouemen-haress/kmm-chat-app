package com.mouemen.azkary.data.remote

import com.mouemen.azkary.data.remote.dto.MessageDto
import com.mouemen.azkary.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

data class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get(MessageService.EndPoints.GetAllMessages.url).body<List<MessageDto>>()
                .map { it.toMessage() }

        } catch (e: Exception) {
            emptyList<Message>()
        }
    }

}