package com.micheldr.spendingtracker.domain.useCase

import android.content.Context
import com.micheldr.spendingtracker.data.SpendingDatabase
import com.micheldr.spendingtracker.data.SpendingRepositoryImpl

class SpendingUseCaseFactory(context: Context) {
    val repository = SpendingRepositoryImpl(
        SpendingDatabase.getDatabase(context).spendingDao(),
    )
    val saveSpendingUseCase by lazy {
        SaveSpendingUseCaseImpl(repository)
    }
    val getSpendingsPaginateUseCase by lazy {
        GetSpendingsPaginateUseCaseImpl(repository)
    }
}