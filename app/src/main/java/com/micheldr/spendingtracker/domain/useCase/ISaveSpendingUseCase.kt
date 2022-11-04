package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.Spending

interface ISaveSpendingUseCase {
    suspend fun execute(spending: Spending)
}