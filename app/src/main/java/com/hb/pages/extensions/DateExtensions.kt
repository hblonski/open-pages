package com.hb.pages.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun LocalDate.format(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}

fun LocalDateTime.format(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}