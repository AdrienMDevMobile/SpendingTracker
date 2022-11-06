package com.micheldr.spendingtracker.view

import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.view.uiState.SpendingUiState

fun Spending.toUiState() = SpendingUiState(
    value = value,
    date = date?.let{ it.toDayString() } ?: "",
    month = date?.let { it.toMonthString() } ?: "",
    year = date?.let { it.toYearString() } ?: "",
    reason = reason,
    highlight = highlight
)