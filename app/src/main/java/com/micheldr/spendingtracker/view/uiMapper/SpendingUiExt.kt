package com.micheldr.spendingtracker.view.uiMapper

import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.view.toDayString
import com.micheldr.spendingtracker.view.toMonthString
import com.micheldr.spendingtracker.view.toYearString
import com.micheldr.spendingtracker.view.uiState.SpendingUiState

fun Spending.toUiState() = SpendingUiState(
    value = value,
    date = date?.toDayString() ?: "",
    month = date?.toMonthString() ?: "",
    year = date?.toYearString() ?: "",
    reason = reason,
    highlight = highlight,
    origin = moneyOrigin
)