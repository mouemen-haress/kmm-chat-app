package org.example.white.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.example.white.domain.repositories.PreferencesRepository

class PreferencesRepositoryImpl(
    settings: Settings
) : PreferencesRepository {

    @OptIn(ExperimentalSettingsApi::class)
    private val flowSettings = (settings as ObservableSettings).toFlowSettings()

    override suspend fun setValue(key: String, value: String) {
        flowSettings.putString(key, value)
    }

    override suspend fun getValue(key: String): String? {
        return flowSettings.getStringOrNull(key)
    }
}