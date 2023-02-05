package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.data.MoneyOrigin
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.view.data.MoneyOriginChoiceClick
import com.micheldr.spendingtracker.view.uiState.AutoDeleteUiState
import com.micheldr.spendingtracker.view.uiState.SavingsListScreenState
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.OffsetDateTime

abstract class SpendingsViewModel() : ViewModel() {
    abstract val amount: MutableState<String>
    abstract val reason: MutableState<String>
    abstract val date: MutableState<OffsetDateTime>
    abstract val amountError: MutableState<Boolean>
    abstract val isHighlight: MutableState<Boolean>
    abstract val moneyOrigin: MutableState<MoneyOrigin>
    abstract val errorMessage: MutableState<SpendingError?>
    abstract val autoDelete: Flow<AutoDeleteUiState>
    abstract val spendingsState: MutableState<SavingsListScreenState>
    abstract val actionsToScreen: LiveData<ActionToScreen?>
    abstract val showMore: MutableState<Boolean>

    sealed class ViewAction {
        class AmountChanged(val amount: String) : ViewAction()
        class ReasonChanged(val reason: String) : ViewAction()
        class DateChanged(val date: OffsetDateTime) : ViewAction()
        class IsHighlightChanged(val highlight: Boolean) : ViewAction()
        class IsMoneyStateChanged(val click: MoneyOriginChoiceClick) : ViewAction()
        class IsAutoDeleteChanged(val activated: Boolean) : ViewAction()
        class AutoDeleteOptionChangedChanged(val value: AutoDeleteChoice) : ViewAction()
        class ShowMore(val show: Boolean) : ViewAction()
        object SaveSpending : ViewAction()
        object LoadSpending : ViewAction()
        object PaginateSpending : ViewAction()
        object ActionToScreenCompleted : ViewAction()

    }

    sealed class ActionToScreen {
        object SavingComplete : ActionToScreen()
    }

    abstract fun notifyViewAction(action: ViewAction)
}