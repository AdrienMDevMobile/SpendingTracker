package com.micheldr.spendingtracker.data

import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.data.SpendingDao
import com.micheldr.spendingtracker.data.SpendingRepository

class SpendingRepositoryImpl(
    private val spendingDao: SpendingDao
) : SpendingRepository {
    override suspend fun saveSpending(spending: Spending) {
        spendingDao.insertAll(spending)
    }

    override suspend fun getSpendings() =
        spendingDao.getAll()
        //https://satyamgondhale-writings.medium.com/jetpack-paging-library-in-android-with-room-database-39e6392c3c47
}