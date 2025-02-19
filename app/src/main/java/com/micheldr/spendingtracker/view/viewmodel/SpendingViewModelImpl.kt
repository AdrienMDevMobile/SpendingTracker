package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.data.MoneyOrigin
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.domain.useCase.*
import com.micheldr.spendingtracker.view.data.MoneyOriginChoiceClick
import com.micheldr.spendingtracker.view.element.paginator.PaginatorImpl
import com.micheldr.spendingtracker.view.uiMapper.toUiState
import com.micheldr.spendingtracker.view.uiState.AutoDeleteUiState
import com.micheldr.spendingtracker.view.uiState.SavingsListScreenState
import com.micheldr.spendingtracker.view.uiState.toUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetDateTime.now

class SpendingViewModelImpl(
    private val saveSpendingUseCase: ISaveSpendingUseCase,
    private val getSpendingsPaginatedUseCase: IGetSpendingsPaginatedUseCase,
    getAutoDeleteUseCase: IGetAutoDeleteUseCase,
    private val setAutoDeleteActivatedUseCase: ISetAutoDeleteActivatedUseCase,
    private val setAutoDeleteChoiceUseCase: ISetAutoDeleteChoiceUseCase,
    private val autoDeleteUseCase: IAutoDeleteUseCase,
    getMoneyOriginUseCase: IMoneyOriginsUseCase,
) : SpendingsViewModel() {
    override val amount = mutableStateOf("")
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(now())
    override val isHighlight = mutableStateOf(false)
    override var moneyOrigin: MutableState<MoneyOrigin>
    override val autoDelete: Flow<AutoDeleteUiState> = getAutoDeleteUseCase.flow.map {
        it.toUiState()
    }
    override val amountError = mutableStateOf(false)
    override val errorMessage: MutableState<SpendingError?> = mutableStateOf(null)
    override val spendingsState = mutableStateOf(SavingsListScreenState())
    override val actionsToScreen = MutableLiveData<ActionToScreen?>(null)
    override val showMore = mutableStateOf(false)

    private val moneyStates = getMoneyOriginUseCase.execute()
    private var currentMoneyStatePosition = 0

    init {
        moneyOrigin = mutableStateOf(moneyStates[currentMoneyStatePosition])
    }

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
            is ViewAction.IsHighlightChanged -> onIsHighlightChanged(action.highlight)
            is ViewAction.IsMoneyStateChanged -> onIsMoneyStateChanged(action.click)
            is ViewAction.IsAutoDeleteChanged -> onIsAutoDeleteChanged(action.activated)
            is ViewAction.AutoDeleteOptionChangedChanged -> onAutoDeleteChoiceChanged(action.value)
            is ViewAction.LoadSpending -> onLoadSpending()
            is ViewAction.PaginateSpending -> loadNextItem()
            is ViewAction.ActionToScreenCompleted -> onActionToScreenCompleted()
            is ViewAction.ShowMore -> onShowMore(action.show)
        }
    }

    private fun onAmountChanged(amount: String) {
        this.amount.value = amount
        showAmountError(!amount.isDigitsOnly())
    }

    private fun onReasonChanged(reason: String) {
        this.reason.value = reason
    }

    private fun onDateChanged(date: OffsetDateTime) {
        this.date.value = date
    }

    private fun onIsHighlightChanged(highlight: Boolean) {
        this.isHighlight.value = highlight
    }

    private fun onIsMoneyStateChanged(click: MoneyOriginChoiceClick) {
        when (click) {
            MoneyOriginChoiceClick.LEFT -> {
                currentMoneyStatePosition++
                if (currentMoneyStatePosition >= moneyStates.size) {
                    currentMoneyStatePosition = 0
                }
            }
            MoneyOriginChoiceClick.RIGHT -> {
                currentMoneyStatePosition--
                if (currentMoneyStatePosition < 0) {
                    currentMoneyStatePosition = moneyStates.size - 1
                }
            }
        }

        moneyOrigin.value = moneyStates[currentMoneyStatePosition]
    }

    private fun onIsAutoDeleteChanged(activated: Boolean) {
        viewModelScope.launch {
            setAutoDeleteActivatedUseCase.execute(activated)
        }
    }

    private fun onAutoDeleteChoiceChanged(choice: AutoDeleteChoice) {
        viewModelScope.launch {
            setAutoDeleteChoiceUseCase.execute(choice)
        }
    }

    private fun onSaveSpending() {
        viewModelScope.launch {
            if (amount.value.isNotEmpty() && amount.value.isDigitsOnly()) {
                showAmountError(false)
                saveSpendingUseCase.execute(
                    Spending(
                        value = amount.value.toInt(),
                        reason = reason.value,
                        date = date.value,
                        highlight = isHighlight.value,
                        moneyOrigin = moneyOrigin.value
                    )
                )
                resetValues()
                showSaveComplet()
            } else {
                showAmountError(true)
            }
        }
    }

    private fun resetValues() {
        amount.value = ""
        reason.value = ""
        errorMessage.value = null
        amountError.value = false
        //highlight, moneyOrigin and dates are NOT reseted
    }

    private fun showSaveComplet() {
        actionsToScreen.value = ActionToScreen.SavingComplete
    }

    private fun showAmountError(isError: Boolean) {
        amountError.value = isError
        if (isError) {
            errorMessage.value = SpendingError.AMOUNT_ERROR
        } else {
            errorMessage.value = null
        }
    }

    private fun loadNextItem() {
        viewModelScope.launch {
            paginator.loadNextItem()
        }
    }

    private fun onLoadSpending() {
        startAutoDelete()
        loadNextItem()
    }

    private fun onActionToScreenCompleted() {
        actionsToScreen.value = null
    }

    private fun onShowMore(show: Boolean) {
        showMore.value = show
    }

    private fun startAutoDelete() {
        viewModelScope.launch {
            autoDeleteUseCase.execute(now())
        }
    }
}