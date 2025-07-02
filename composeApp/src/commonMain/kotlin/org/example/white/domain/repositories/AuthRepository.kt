package org.example.white.domain.repositories

import org.example.white.data.remote.dto.LoginResponse

interface AuthRepository {

    open suspend fun login(displayName: String, password: String): LoginResponse
}