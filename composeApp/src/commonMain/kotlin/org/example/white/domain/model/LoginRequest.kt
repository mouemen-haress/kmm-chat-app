package org.example.white.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val displayName: String,
    val password: String
)
