package org.example.white.domain.repositories

interface PreferencesRepository {
    suspend fun setValue(key: String, value: String)
    suspend fun getValue(key: String): String?
}