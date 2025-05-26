package org.example.white.data.remote

import org.example.white.domain.model.Message
import org.example.white.util.Resource
import kotlinx.coroutines.flow.Flow
import org.example.white.Constants

interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeConnection()

    companion object {
        const val BASE_URL = "ws://${Constants.BASE_URL}:8080"
    }

    sealed class EndPoints(val url:String){
        object ChatSocket:EndPoints("$BASE_URL/chat-socket")
    }
}