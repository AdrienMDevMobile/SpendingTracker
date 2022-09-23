package com.micheldr.spendingtracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.micheldr.spendingtracker.viewmodel.SpendingUiState
import com.micheldr.spendingtracker.viewmodel.SpendingsViewModel
import org.threeten.bp.OffsetDateTime.now

class SpendingViewModelMock : SpendingsViewModel() {
    override val amount = mutableStateOf(0)
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(now())
    override val spendingList = mutableStateOf(emptyList<SpendingUiState>())

    override fun notifyViewAction(action: ViewAction) {
        TODO("Not yet implemented")
    }
}