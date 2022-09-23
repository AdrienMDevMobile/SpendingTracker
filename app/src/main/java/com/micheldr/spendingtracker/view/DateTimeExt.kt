package com.micheldr.spendingtracker.view

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

fun OffsetDateTime.toMyString(): String = this.toZonedDateTime()
    .format(DateTimeFormatter.ofPattern("d MMMM yyyy"))