package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.CurrentDateRepository
import com.micheldr.spendingtracker.data.SpendingRepository

class AutoDeleteUseCaseImpl(
    private val currentDateRepository: CurrentDateRepository,
    private val spendingRepository: SpendingRepository,
) : IAutoDeleteUseCase {
    override suspend fun execute(page: Int): Result<Unit> {
        return spendingRepository.deleteSpendings(currentDateRepository.getCurrentDate())
    }
}