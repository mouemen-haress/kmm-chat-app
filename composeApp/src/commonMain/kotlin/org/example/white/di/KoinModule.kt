package org.example.white.di

import com.mouemen.azkary.data.remote.ChatSocketService
import com.mouemen.azkary.data.remote.ChatSocketServiceImpl
import com.mouemen.azkary.data.remote.MessageService
import com.mouemen.azkary.data.remote.MessageServiceImpl
import com.mouemen.azkary.presentation.chat.ChatViewModel
import com.mouemen.azkary.presentation.ussername.UsernameViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.white.provideHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
        provideHttpClient()
    }

    single<MessageService> {
        MessageServiceImpl(get())
    }

    single<ChatSocketService> { ChatSocketServiceImpl(get()) }

    factory {
        UsernameViewModel()
    }

    factory { ChatViewModel(get(), get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}