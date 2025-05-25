package com.mouemen.azkary.data.remote


import com.mouemen.azkary.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://192.168.1.111:8080"
    }

    sealed class EndPoints(val url:String){
        object GetAllMessages:EndPoints("$BASE_URL/messages")
    }
}