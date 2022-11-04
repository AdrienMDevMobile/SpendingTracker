package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.SpendingRepository

class GetSpendingsPaginateUseCaseImpl(
    private val repository: SpendingRepository
) : IGetSpendingsPaginatedUseCase {
    val firstPageQuantity = 20
    val pageQuantity = 10

    override suspend fun execute(page: Int) =
        repository.getSpendingsPaginated(getPaginationOffset(page), getPaginationAmount(page))

    private fun getPaginationOffset(page: Int) = if (page < 0) {
        throw Exception("Offset below 0")
    } else if (page == 0) {
        0
    } else {
        firstPageQuantity + (page - 1) * pageQuantity
    }

    private fun getPaginationAmount(page: Int) = if (page == 0) {
        firstPageQuantity
    } else {
        pageQuantity
    }
}