package com.micheldr.spendingtracker.data

import org.threeten.bp.OffsetDateTime

interface SpendingRepository {
    suspend fun saveSpending(spending: Spending)
    suspend fun getSpendingsPaginated(offset: Int, amount: Int): Result<List<Spending>>
    suspend fun deleteSpendings(beforeDate: OffsetDateTime): Result<Unit>
}