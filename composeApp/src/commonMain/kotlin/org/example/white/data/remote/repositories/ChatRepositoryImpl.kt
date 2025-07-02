package org.example.white.data.remote.repositories

import org.example.white.data.remote.ApiService
import org.example.white.data.remote.dto.ChatsDto
import org.example.white.domain.repositories.ChatRepository

class ChatRepositoryImpl(
    private val apiService: ApiService,
) : ChatRepository {


    override suspend fun getUserChats(): ArrayList<ChatsDto> {
        return apiService.getUserChats()
    }
}