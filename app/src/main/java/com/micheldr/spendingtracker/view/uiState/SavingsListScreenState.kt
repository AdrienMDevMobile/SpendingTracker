package com.micheldr.spendingtracker.view.uiState

data class SavingsListScreenState(
    val isLoading: Boolean = false,
    val items: List<SpendingUiState> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)