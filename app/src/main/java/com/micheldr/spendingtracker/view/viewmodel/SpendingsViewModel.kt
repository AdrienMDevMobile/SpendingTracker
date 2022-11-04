package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.view.SavingsListScreenState
import org.threeten.bp.OffsetDateTime

abstract class SpendingsViewModel() : ViewModel() {

    abstract val amount: MutableState<String>
    abstract val reason: MutableState<String>
    abstract val date: MutableState<OffsetDateTime>
    abstract val amountError: MutableState<Boolean>
    abstract val errorMessage: MutableState<SpendingError?>

    abstract val spendingsState: MutableState<SavingsListScreenState>

    sealed class ViewAction {
        class AmountChanged(val amount: String) : ViewAction()
        class ReasonChanged(val reason: String) : ViewAction()
        class DateChanged(val date: OffsetDateTime) : ViewAction()
        object SaveSpending : ViewAction()
        object LoadSpending : ViewAction()
    }

    abstract fun notifyViewAction(action: ViewAction)
}