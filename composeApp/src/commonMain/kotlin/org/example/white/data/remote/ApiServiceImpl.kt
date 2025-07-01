package org.example.white.data.remote

import com.russhwolf.settings.Settings
import org.example.white.data.remote.dto.MessageDto
import org.example.white.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import org.example.white.Constants
import org.example.white.domain.model.Chat
import org.example.white.domain.model.LoginRequest
import org.example.white.domain.model.LoginResponse
import org.example.white.domain.repositories.PreferencesRepository

data class ApiServiceImpl(
    private val client: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : ApiService {

    override suspend fun getAllMessages(chatId: String): List<Message> {
        return try {
            client.get("${ApiService.EndPoints.GetAllMessages.url}?chatId=$chatId")
                .body<List<MessageDto>>()
                .map { it.toMessage() }

        } catch (e: Exception) {
            println(e.message)
            emptyList<Message>()
        }
    }

    override suspend fun getUserChats(): ArrayList<Chat> {
        return try {
            val requestUrl = "${ApiService.EndPoints.GetMyChats.url}?userId=${
                preferencesRepository.getValue(
                    org.example.white.util.Constants.MY_LOGGED_IN_ID
                )
            }"
            client.get(requestUrl).body<ArrayList<Chat>>()
        } catch (e: Exception) {
            println(e.message)
            arrayListOf<Chat>()
        }
    }

    override suspend fun login(displayName: String, password: String): LoginResponse {
        val request = LoginRequest(displayName, password)
        return try {
            val response: HttpResponse = client.post(ApiService.EndPoints.Auth.url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if (response.status == HttpStatusCode.OK) {
                response.body<LoginResponse>()
            } else {
                val errorMap = response.body<Map<String, String>>()
                LoginResponse(message = "Failed", error = errorMap["error"])
            }

        } catch (e: Exception) {
            LoginResponse(message = "Exception", error = e.message)
        }
    }

}