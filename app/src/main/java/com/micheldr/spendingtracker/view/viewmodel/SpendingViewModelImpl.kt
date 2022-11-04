package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.domain.useCase.IGetSpendingsPaginatedUseCase
import com.micheldr.spendingtracker.domain.useCase.ISaveSpendingUseCase
import com.micheldr.spendingtracker.view.SavingsListScreenState
import com.micheldr.spendingtracker.view.element.paginator.PaginatorImpl
import com.micheldr.spendingtracker.view.toUiState
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetDateTime.now

class SpendingViewModelImpl(
    private val saveSpendingUseCase: ISaveSpendingUseCase,
    private val getSpendingsPaginatedUseCase: IGetSpendingsPaginatedUseCase
) : SpendingsViewModel() {
    override val amount = mutableStateOf("")
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(now())
    override val amountError = mutableStateOf(false)
    override val errorMessage: MutableState<SpendingError?> = mutableStateOf(null)
    override val spendingsState = mutableStateOf(SavingsListScreenState())

    //A injecter
    private val paginator = PaginatorImpl(
        initalKey = spendingsState.value.page,
        onLoadUpdated = {
            spendingsState.value = spendingsState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getSpendingsPaginatedUseCase.execute(nextPage)
        },
        getNextKey = {
            spendingsState.value.page + 1
        },
        onError = {
            spendingsState.value = spendingsState.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            spendingsState.value = spendingsState.value.copy(
                items = spendingsState.value.items + items.map { it.toUiState() },
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    override fun notifyViewAction(action: ViewAction) {
        when (action) {
            ViewAction.SaveSpending -> onSaveSpending()
            is ViewAction.AmountChanged -> onAmountChanged(action.amount)
            is ViewAction.ReasonChanged -> onReasonChanged(action.reason)
            is ViewAction.DateChanged -> onDateChanged(action.date)
            is ViewAction.LoadSpending -> onLoadSpending()
        }
    }

    private fun onAmountChanged(amount: String) {
        this.amount.value = amount
        showAmountError(amount.isDigitsOnly())

    }

    private fun onReasonChanged(reason: String) {
        this.reason.value = reason
    }

    private fun onDateChanged(date: OffsetDateTime) {
        this.date.value = date
    }

    private fun onSaveSpending() {
        viewModelScope.launch {
            if (!amount.value.isNullOrEmpty() && amount.value.isDigitsOnly()) {
                showAmountError(false)
                saveSpendingUseCase.execute(
                    Spending(
                        value = amount.value.toInt(),
                        reason = reason.value,
                        date = date.value
                    )
                )
            } else {
                showAmountError(true)
            }
        }
    }

    private fun showAmountError(isError: Boolean) {
        amountError.value = isError
        if (isError) {
            errorMessage.value = null
        } else {
            errorMessage.value = SpendingError.AMOUNT_ERROR
        }
    }

    private fun showSaveError(isError: Boolean) {
        amountError.value = isError
        if (isError) {
            errorMessage.value = null
        } else {
            errorMessage.value = SpendingError.SAVE_ERROR
        }
    }

    fun loadNextItem() {
        viewModelScope.launch {
            paginator.loadNextItem()
        }
    }

    private fun onLoadSpending() {
        loadNextItem()
    }
}