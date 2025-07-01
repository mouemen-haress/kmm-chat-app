package org.example.white.data.remote.repositories

import org.example.white.data.remote.ApiService
import org.example.white.domain.model.Chat
import org.example.white.domain.model.LoginResponse
import org.example.white.domain.repositories.AuthRepository
import org.example.white.domain.repositories.ChatRepository

class ChatRepositoryImpl(
    private val apiService: ApiService,
) : ChatRepository {


    override suspend fun getUserChats(): ArrayList<Chat> {
        return apiService.getUserChats()
    }
}