package org.example.white.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
    val userId: String? = null,
    val error: String? = null
)