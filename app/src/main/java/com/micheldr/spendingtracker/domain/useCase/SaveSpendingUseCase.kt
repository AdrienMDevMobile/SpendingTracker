package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.data.SpendingRepository

class SaveSpendingUseCaseImpl(
    private val repository: SpendingRepository
) : ISaveSpendingUseCase {
    override suspend fun execute(spending: Spending) {
        repository.saveSpending(spending)
    }
}