package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.MoneyOrigin

interface IMoneyOriginsUseCase {
    fun execute() : List<MoneyOrigin>
}