package org.example.white.data.remote


import org.example.white.Constants
import org.example.white.data.remote.dto.ChatsDto
import org.example.white.data.remote.dto.LoginResponse
import org.example.white.domain.model.Message

interface ApiService {
    suspend fun getAllMessages(chatId: String): List<Message>
    suspend fun getUserChats(): ArrayList<ChatsDto>
    suspend fun login(displayName: String, password: String): LoginResponse

    companion object {
        const val BASE_URL = "http://${Constants.BASE_URL}:8080"
    }

    sealed class EndPoints(val url: String) {
        object GetAllMessages : EndPoints("$BASE_URL/messages")
        object GetMyChats : EndPoints("$BASE_URL/user-chats")
        object Auth : EndPoints("$BASE_URL/login")
    }
}