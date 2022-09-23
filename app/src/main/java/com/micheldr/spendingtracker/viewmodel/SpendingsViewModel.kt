package com.micheldr.spendingtracker.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import org.threeten.bp.OffsetDateTime

abstract class SpendingsViewModel : ViewModel() {

    abstract val amount: MutableState<Int>
    abstract val reason: MutableState<String>
    abstract val date: MutableState<OffsetDateTime>

    abstract val spendingList: MutableState<List<SpendingUiState>>

    sealed class ViewAction {
        class AmountChanged(val amount: Int) : ViewAction()
        class ReasonChanged(val reason: String) : ViewAction()
        class DateChanged(val date: OffsetDateTime) : ViewAction()
        object SaveSpending : ViewAction()
        object LoadSpending : ViewAction()
    }

    abstract fun notifyViewAction(action: ViewAction)
}