package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.MoneyOrigin

class MoneyOriginsUseCaseImpl: IMoneyOriginsUseCase {
    override fun execute(): List<MoneyOrigin> = listOf(
        MoneyOrigin.CREDIT_CARD,
        MoneyOrigin.CASH,
        MoneyOrigin.CHEQUE
    )
}