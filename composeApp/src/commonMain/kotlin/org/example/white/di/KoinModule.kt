package org.example.white.di

import com.russhwolf.settings.Settings
import org.example.white.data.remote.ChatSocketService
import org.example.white.data.remote.ChatSocketServiceImpl
import org.example.white.data.remote.ApiService
import org.example.white.data.remote.ApiServiceImpl
import org.example.white.presentation.chat.ChatViewModel
import io.ktor.client.HttpClient
import org.example.white.data.local.PreferencesRepositoryImpl
import org.example.white.data.remote.repositories.AuthRepositoryImpl
import org.example.white.data.remote.repositories.ChatRepositoryImpl
import org.example.white.domain.repositories.AuthRepository
import org.example.white.domain.repositories.ChatRepository
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.domain.use_cases.ConnectToSocketUseCase
import org.example.white.presentation.`chat-list`.ChatListViewModel
import org.example.white.presentation.onBoarding.OnBoardingViewModel
import org.example.white.provideHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
        provideHttpClient()
    }

    single<ApiService> {
        ApiServiceImpl(get(), get())
    }

    single {
        Settings()
    }

    single<ChatSocketService> { ChatSocketServiceImpl(get()) }

    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<ChatRepository> { ChatRepositoryImpl(get()) }

    factory { OnBoardingViewModel(get(), get()) }

    factory {
        ChatListViewModel(get(), get())
    }
    single {
        ConnectToSocketUseCase(get(), get())
    }

    factory { ChatViewModel(get(), get(), get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}