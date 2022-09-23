package com.micheldr.spendingtracker.data

interface SpendingRepository {
    suspend fun saveSpending(spending: Spending)
    suspend fun getSpendings(): List<Spending>
}