package org.example.white.di

import org.example.white.data.remote.ChatSocketService
import org.example.white.data.remote.ChatSocketServiceImpl
import org.example.white.data.remote.MessageService
import org.example.white.data.remote.MessageServiceImpl
import org.example.white.presentation.chat.ChatViewModel
import io.ktor.client.HttpClient
import org.example.white.presentation.onBoarding.OnBoardingViewModel
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
        OnBoardingViewModel()
    }

    factory { ChatViewModel(get(), get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}