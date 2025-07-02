package org.example.white.util

import kotlinx.datetime.*

object DateHelper {

    fun getCurrentTime(): Long {
        val nowInstant = Clock.System.now()
        return nowInstant.epochSeconds
    }

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

    fun formatTimestamp(timestampMillis: Long): String {
        val instant = Instant.fromEpochMilliseconds(timestampMillis)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val date = "${dateTime.dayOfMonth}/${dateTime.monthNumber}/${dateTime.year}"
        val time = "${dateTime.hour}:${dateTime.minute.toString().padStart(2, '0')}"

        return "$date $time"
    }
}