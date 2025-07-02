package org.example.white.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
    val userId: String? = null,
    val error: String? = null
)