package org.example.white.domain.use_cases

import org.example.white.data.remote.ChatSocketService
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.util.Resource

class ConnectToSocketUseCase(
    val socketService: ChatSocketService,
    val preferencesRepository: PreferencesRepository
) {

    suspend fun invoke() {
        connectToSocket()
    }

    private suspend fun connectToSocket() {
        val username = preferencesRepository.getValue(
            org.example.white.util.Constants.MY_LOGGED_IN_ID
        ) ?: ""
        val result = socketService.initSession(username)
        when (result) {
            is Resource.Error<*> -> {

            }

            is Resource.Success<*> -> {

            }
        }

    }
}