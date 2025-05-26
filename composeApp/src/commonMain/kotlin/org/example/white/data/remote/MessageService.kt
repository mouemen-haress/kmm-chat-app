package org.example.white.data.remote


import org.example.white.Constants
import org.example.white.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://${Constants.BASE_URL}:8080"
    }

    sealed class EndPoints(val url: String) {
        object GetAllMessages : EndPoints("$BASE_URL/messages")
    }
}