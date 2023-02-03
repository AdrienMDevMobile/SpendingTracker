package com.micheldr.spendingtracker.data

import org.threeten.bp.OffsetDateTime

interface CurrentDateRepository {
    suspend fun getCurrentDate(): OffsetDateTime
}