package org.example.white.util

import kotlinx.datetime.*

object DateHelper {
    fun getCurrentFormattedTime(): String {
        val nowInstant = Clock.System.now()
        val localDateTime = nowInstant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = localDateTime.hour.toString().padStart(2, '0')
        val minute = localDateTime.minute.toString().padStart(2, '0')

        // Capitalize month name short form
        val month = localDateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)

        val day = localDateTime.dayOfMonth.toString().padStart(2, '0')

        return "$hour:$minute $month $day"
    }
}