package org.example.white.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val displayName: String,
    val password: String
)
