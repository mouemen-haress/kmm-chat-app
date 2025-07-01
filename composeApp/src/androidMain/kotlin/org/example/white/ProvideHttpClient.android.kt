package org.example.white

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

actual fun provideHttpClient(): HttpClient = HttpClient(CIO) {
    install(WebSockets)
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                when {
                    message.startsWith("[ERROR]") -> Napier.e(message, tag = "Ktor")
                    message.startsWith("[WARN]") -> Napier.w(message, tag = "Ktor")
                    message.startsWith("[INFO]") -> Napier.i(message, tag = "Ktor")
                    else -> Napier.d(message, tag = "Ktor")
                }
            }
        }
        level = LogLevel.ALL
    }

}