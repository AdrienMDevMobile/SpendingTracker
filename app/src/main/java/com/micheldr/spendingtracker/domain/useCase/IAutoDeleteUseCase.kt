package com.micheldr.spendingtracker.domain.useCase

import org.threeten.bp.OffsetDateTime

interface IAutoDeleteUseCase {
    suspend fun execute(date: OffsetDateTime): Result<Unit>
}