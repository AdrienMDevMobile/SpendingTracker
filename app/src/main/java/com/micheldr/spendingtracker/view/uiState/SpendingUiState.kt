package com.micheldr.spendingtracker.view.uiState

import com.micheldr.spendingtracker.data.MoneyOrigin
import com.micheldr.spendingtracker.view.element.paginator.Item

//https://betterprogramming.pub/double-header-lazycolumn-in-jetpack-compose-5cbbcece75ec
data class SpendingUiState(
    val value: String,
    val date: String,
    val month: String,
    val year: String,
    val reason: String,
    val highlight: Boolean = false,
    val origin: MoneyOrigin,
) : Item(
    year,
    month,
)