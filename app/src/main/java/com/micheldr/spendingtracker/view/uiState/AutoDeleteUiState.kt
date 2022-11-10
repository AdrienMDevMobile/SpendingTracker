package com.micheldr.spendingtracker.view.uiState

import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.data.AutoDeleteOption

data class AutoDeleteUiState(
    val isActivated: Boolean,
    val autoDeleteChosen: AutoDeleteChoice
)

fun AutoDeleteOption.toUiState() = AutoDeleteUiState(
    isActivated = activated,
    autoDeleteChosen = choice
)