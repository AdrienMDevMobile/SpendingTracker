package com.micheldr.spendingtracker.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.data.SpendingRepository
import com.micheldr.spendingtracker.view.toUiState
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetDateTime.now

class SpendingViewModelImpl(private val repository: SpendingRepository) : SpendingsViewModel() {
    override val amount = mutableStateOf(0)
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(now())
    override val spendingList: MutableState<List<SpendingUiState>> = mutableStateOf(emptyList())

    override fun notifyViewAction(action: ViewAction) {
        when (action) {
            ViewAction.SaveSpending -> onSaveSpending()
            is ViewAction.AmountChanged -> onAmountChanged(action.amount)
            is ViewAction.ReasonChanged -> onReasonChanged(action.reason)
            is ViewAction.DateChanged -> onDateChanged(action.date)
            is ViewAction.LoadSpending -> onLoadSpending()
        }
    }

    private fun onAmountChanged(amount: Int) {
        this.amount.value = amount
    }

    private fun onReasonChanged(reason: String) {
        this.reason.value = reason
    }

    private fun onDateChanged(date: OffsetDateTime) {
        this.date.value = date
    }

    private fun onSaveSpending() {
        viewModelScope.launch {
            repository.saveSpending(Spending(value = amount.value, reason = reason.value, date = date.value))
        }
    }

    private fun onLoadSpending() {
        viewModelScope.launch {
            spendingList.value = repository.getSpendings().map {
                it.toUiState()
            }
        }
    }
}