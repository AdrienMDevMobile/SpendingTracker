package com.micheldr.spendingtracker.data

interface SpendingRepository {
    suspend fun saveSpending(spending: Spending)
    suspend fun getSpendingsPaginated(offset: Int, amount: Int): Result<List<Spending>>
}