package org.example.white.domain.repositories

import org.example.white.domain.model.Chat
import org.example.white.domain.model.LoginResponse

interface ChatRepository {

    open suspend fun getUserChats(): ArrayList<Chat>

}