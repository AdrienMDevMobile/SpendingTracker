package com.micheldr.spendingtracker.view.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.view.toUiState
import com.micheldr.spendingtracker.view.uiState.LoadingButtonUiState
import com.micheldr.spendingtracker.view.uiState.SavingsListScreenState
import org.threeten.bp.*

class SpendingViewModelMock(showElement:Boolean = true) : SpendingsViewModel() {
    override val amount = mutableStateOf("0")
    override val reason = mutableStateOf("")
    override val date = mutableStateOf(
        OffsetDateTime.of(LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT, ZoneOffset.UTC)
    )
    override val amountError = mutableStateOf(true)
    override val isCheque: MutableState<Boolean> = mutableStateOf(true)
    override val autoDeleteActivated: MutableState<Boolean> = mutableStateOf(true)
    override val errorMessage: MutableState<SpendingError?> = mutableStateOf(SpendingError.AMOUNT_ERROR)
    override val spendingsState: MutableState<SavingsListScreenState> = mutableStateOf(
        SavingsListScreenState(items = emptyList())
    )

    init {
        if (showElement) {
            spendingsState.value = SavingsListScreenState(
                items = listOf(
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = false
                    ).toUiState(),
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2021, Month.DECEMBER, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = false
                    ).toUiState(),
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2021, Month.DECEMBER, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = false
                    ).toUiState(),
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2021, Month.NOVEMBER, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = true
                    ).toUiState(),
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2021, Month.OCTOBER, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = false
                    ).toUiState(),
                    Spending(
                        value = 50,
                        reason = "Test reason---------------------------",
                        date = OffsetDateTime.of(
                            LocalDate.of(2021, Month.APRIL, 21), LocalTime.MIDNIGHT,
                            ZoneOffset.UTC
                        ),
                        highlight = false
                    ).toUiState()
                )
            )
        }

    }

    override fun notifyViewAction(action: ViewAction) {}
}