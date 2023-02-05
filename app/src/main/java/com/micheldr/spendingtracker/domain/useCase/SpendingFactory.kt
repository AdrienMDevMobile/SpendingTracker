package com.micheldr.spendingtracker.domain.useCase

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.adrienmandroid.datastore.useCase.SetAutoDeleteActivatedUseCaseImpl
import com.micheldr.spendingtracker.data.AutoDeleteRepositoryImpl
import com.micheldr.spendingtracker.data.SpendingDatabase
import com.micheldr.spendingtracker.data.SpendingRepositoryImpl
import com.micheldr.spendingtracker.view.viewmodel.SpendingViewModelImpl

class SpendingFactory(context: Context, dataStore: DataStore<Preferences>) {


    private val spendingDatabase = SpendingDatabase.getDatabase(context).spendingDao()
    private val repository = SpendingRepositoryImpl(
        spendingDatabase
    )
    private val autoDeleteRepository = AutoDeleteRepositoryImpl(dataStore)
    private val saveSpendingUseCase by lazy {
        SaveSpendingUseCaseImpl(repository)
    }
    private val getSpendingsPaginateUseCase by lazy {
        GetSpendingsPaginateUseCaseImpl(repository)
    }
    private val getGetAutoDeleteChoiceUseCase by lazy {
        GetAutoDeleteUseCaseImpl(autoDeleteRepository)
    }
    private val getSetAutoDeleteActivatedUseCase by lazy {
        SetAutoDeleteActivatedUseCaseImpl(autoDeleteRepository)
    }
    private val getSetAutoDeleteChoiceUseCaseImpl by lazy {
        SetAutoDeleteChoiceUseCaseImpl(autoDeleteRepository)
    }
    private val getAutoDeleteRepository by lazy {
        AutoDeleteUseCaseImpl(repository, autoDeleteRepository)
    }

    val getSpendingViewModel = SpendingViewModelImpl(
        saveSpendingUseCase = saveSpendingUseCase,
        getSpendingsPaginatedUseCase = getSpendingsPaginateUseCase,
        getAutoDeleteUseCase = getGetAutoDeleteChoiceUseCase,
        setAutoDeleteActivatedUseCase = getSetAutoDeleteActivatedUseCase,
        setAutoDeleteChoiceUseCase = getSetAutoDeleteChoiceUseCaseImpl,
        autoDeleteUseCase = getAutoDeleteRepository,
        getMoneyOriginUseCase = MoneyOriginsUseCaseImpl()
    )
}