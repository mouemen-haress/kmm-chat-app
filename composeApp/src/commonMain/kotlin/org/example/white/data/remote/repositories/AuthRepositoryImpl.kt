package org.example.white.data.remote.repositories

import org.example.white.data.remote.ApiService
import org.example.white.domain.model.LoginResponse
import org.example.white.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService,
) : AuthRepository {

    override suspend fun login(displayName: String, password: String): LoginResponse {
        return apiService.login(displayName, password)
    }
}