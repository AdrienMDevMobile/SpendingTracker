package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.Spending


interface IGetSpendingsPaginatedUseCase {
    suspend fun execute(page: Int): Result<List<Spending>>
}