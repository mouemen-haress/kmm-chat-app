package com.mouemen.azkary.data.remote

import com.mouemen.azkary.domain.model.Message
import com.mouemen.azkary.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeConnection()

    companion object {
        const val BASE_URL = "ws://192.168.1.111:8080"
    }

    sealed class EndPoints(val url:String){
        object ChatSocket:EndPoints("$BASE_URL/chat-socket")
    }
}