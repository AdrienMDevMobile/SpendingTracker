package com.micheldr.spendingtracker.domain.useCase

interface IAutoDeleteUseCase {
    suspend fun execute(page: Int): Result<Unit>
}