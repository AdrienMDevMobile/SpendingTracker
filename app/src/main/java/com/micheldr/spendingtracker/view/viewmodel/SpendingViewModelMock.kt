package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.view.uiState.LoadingButtonUiState
import com.micheldr.spendingtracker.view.uiState.SavingsListScreenState
import org.threeten.bp.*

class SpendingViewModelMock() : SpendingsViewModel() {
    override val amount = mutableStateOf("0")
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(
        OffsetDateTime.of(LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT, ZoneOffset.UTC)
    )
    override val amountError = mutableStateOf(true)
    override val errorMessage: MutableState<SpendingError?> = mutableStateOf(SpendingError.AMOUNT_ERROR)
    override val loadingButtonState: MutableState<LoadingButtonUiState> = mutableStateOf(LoadingButtonUiState())
    override val spendingsState: MutableState<SavingsListScreenState> = mutableStateOf(SavingsListScreenState())

    override fun notifyViewAction(action: ViewAction) {
        TODO("Not yet implemented")
    }
}