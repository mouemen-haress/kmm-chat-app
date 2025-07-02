package org.example.white.domain.repositories

import org.example.white.data.remote.dto.ChatsDto

interface ChatRepository {

    open suspend fun getUserChats(): ArrayList<ChatsDto>

}