package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.AutoDeleteOption
import kotlinx.coroutines.flow.Flow

interface IGetAutoDeleteUseCase {
    val flow: Flow<AutoDeleteOption>
}

