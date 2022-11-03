package com.micheldr.spendingtracker.view

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

fun OffsetDateTime.toShortString(): String = this.toZonedDateTime()
    .format(DateTimeFormatter.ofPattern("d MM yyyy"))

fun OffsetDateTime.toLongString(): String = this.toZonedDateTime()
    .format(DateTimeFormatter.ofPattern("d MMMM yyyy"))