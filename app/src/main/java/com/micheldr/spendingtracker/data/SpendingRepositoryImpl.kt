package com.micheldr.spendingtracker.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.OffsetDateTime

class SpendingRepositoryImpl(
    private val spendingDao: SpendingDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SpendingRepository {

    override suspend fun saveSpending(spending: Spending) {
        spendingDao.insertAll(spending)
    }

    //https://satyamgondhale-writings.medium.com/jetpack-paging-library-in-android-with-room-database-39e6392c3c47

    override suspend fun getSpendingsPaginated(offset: Int, amount: Int) = withContext(dispatcher) {
        Result.success(spendingDao.getSpendingsPaged(offset, amount))
    }


    override suspend fun deleteSpendings(beforeDate: OffsetDateTime) = withContext(dispatcher){
        Result.success(spendingDao.deleteBeforeDate(beforeDate))
    }
    //TODO : problème de pagination si un élément est supprimé : avoir une variable pour connaitre les éléments qui ont étés supprimés

}
