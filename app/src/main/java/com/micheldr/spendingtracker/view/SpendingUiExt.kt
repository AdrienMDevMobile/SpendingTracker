package com.micheldr.spendingtracker.view

import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.view.viewmodel.SpendingUiState

fun Spending.toUiState() = SpendingUiState(
    value = value,
    date = date?.let{ it.toShortString() } ?: "",
    reason = reason
)