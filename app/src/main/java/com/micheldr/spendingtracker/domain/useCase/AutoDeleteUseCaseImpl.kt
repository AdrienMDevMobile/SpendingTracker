package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.data.AutoDeleteRepository
import com.micheldr.spendingtracker.data.SpendingRepository
import org.threeten.bp.Duration
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.TemporalAmount

class AutoDeleteUseCaseImpl(
    private val spendingRepository: SpendingRepository,
    private val autoDeleteRepository: AutoDeleteRepository,
) : IAutoDeleteUseCase {
    override suspend fun execute(date: OffsetDateTime): Result<Unit> {
        val autoDelete = autoDeleteRepository.getUserAutoDeletePreferences()

        if (!autoDelete.activated) {
            return Result.success(Unit)
        }
        val dateDiffernce: TemporalAmount = when (autoDelete.choice) {
            AutoDeleteChoice.ONE_WEEK -> Duration.ofDays(7)
            AutoDeleteChoice.ONE_MONTH -> Duration.ofDays(30)
            AutoDeleteChoice.THREE_MONTH -> Duration.ofDays(90)
            AutoDeleteChoice.ONE_YEAR -> Duration.ofDays(365)
        }
        return spendingRepository.deleteSpendings(date.minus(dateDiffernce))
    }
}