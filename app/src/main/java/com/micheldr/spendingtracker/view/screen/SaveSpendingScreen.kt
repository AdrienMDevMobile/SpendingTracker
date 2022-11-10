package com.micheldr.spendingtracker.view.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.micheldr.spendingtracker.view.element.MoneyOriginChoice
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.element.*
import com.micheldr.spendingtracker.view.uiState.AutoDeleteUiState
import com.micheldr.spendingtracker.view.viewmodel.SpendingViewModelMock
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.LocalDateTime.of as ofdate
import org.threeten.bp.OffsetDateTime.of as ofodt

val buttonHeight = 50.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SaveSpendingScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier) {

        SaveErrorMessage(
            error = viewModel.errorMessage.value
        )

        val reasonFocusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        SaveTextField(
            value = viewModel.amount.value, onValueChanged = { newValue ->
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction
                        .AmountChanged(newValue)
                )
            }, leadingIcon = R.drawable.ic_baseline_euro_24,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = viewModel.amountError.value,
            keyboardActions = KeyboardActions(onDone = { reasonFocusRequester.requestFocus() })
        )

        Spacer(modifier = Modifier.height(10.dp))

        SaveTextField(
            value = viewModel.reason.value,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(reasonFocusRequester),
            onValueChanged = { newValue ->
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.ReasonChanged(newValue))
            },
            placeholder = R.string.reason_field,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )


        Spacer(modifier = Modifier.height(10.dp))

        if (viewModel.showMore.value) {
            OptionShowMore(viewModel, context)
        } else {
            OptionShowLess(viewModel)
        }

    }
}

@Composable
fun OptionShowMore(
    viewModel: SpendingsViewModel,
    context: Context
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (leftColumn, middleColumn, rightColumn) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .constrainAs(leftColumn) {
                    end.linkTo(middleColumn.start)
                }, horizontalAlignment = Alignment
                .CenterHorizontally
        ) {

            DateButton(
                value = viewModel.date.value,
                context = context
            ) { mYear, mMonth, mDayOfMonth ->
                onDateClick(viewModel, mYear, mMonth, mDayOfMonth)
            }

            Spacer(modifier = Modifier.height(10.dp))

            HighlightButton(
                isHighlight = viewModel.isHighlight.value,
                modifier = Modifier.size(50.dp, 50.dp)
            ) { isHighlight ->
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction.IsHighlightChanged(
                        isHighlight
                    )
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .height(buttonHeight)
                .constrainAs(middleColumn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            ImageButton(
                icon = R.drawable.ic_baseline_save_24,
                onClick = {
                    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.SaveSpending)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            MoneyOriginChoice(viewModel.moneyOrigin.value) { state ->
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction.IsMoneyStateChanged(state)
                )
            }
        }

        val autoDelete = viewModel.autoDelete.collectAsState(
            initial = AutoDeleteUiState(
                false,
                AutoDeleteChoice.ONE_YEAR
            )
        )

        AutoDelete(uiState = autoDelete.value, modifier = Modifier
            .fillMaxWidth(0.35f)
            .constrainAs(rightColumn) {
                start.linkTo(middleColumn.end)
            }, onActivation = {
            viewModel.notifyViewAction(
                SpendingsViewModel.ViewAction.IsAutoDeleteChanged(it)
            )
        }, onTimeLimitChanged = { })
    }
}

@Composable
fun OptionShowLess(
    viewModel: SpendingsViewModel,
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (middleColumn, rightColumn) = createRefs()

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .height(buttonHeight)
                .constrainAs(middleColumn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            ImageButton(
                icon = R.drawable.ic_baseline_save_24,
            ) {
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.SaveSpending)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(buttonHeight)
                .constrainAs(rightColumn) {
                    start.linkTo(middleColumn.end)
                }) {
            ImageButton(
                icon = R.drawable.ic_baseline_keyboard_arrow_up_24,
            ) {
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.ShowMore(true))
            }
        }
    }
}

fun datePicker(
    context: Context,
    listener: DatePickerDialog.OnDateSetListener,
    date: OffsetDateTime
) =
    DatePickerDialog(
        context,
        listener,
        date.year, date.monthValue, date.dayOfMonth
    )

fun onDateClick(viewModel: SpendingsViewModel, mYear: Int, mMonth: Int, mDayOfMonth: Int) {
    val date = ofdate(mYear, mMonth + 1, mDayOfMonth, 0, 0)
    val odt = ofodt(date, OffsetDateTime.now().offset)
    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.DateChanged(odt))
}

//TODO enchainer datepickerdialog avec timepickerdialog

@Preview(showBackground = true)
@Composable
fun SaveScreenPreviewMore() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SaveSpendingScreen(SpendingViewModelMock(showMore = true))
    }
}

@Preview(showBackground = true)
@Composable
fun SaveScreenPreviewLess() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SaveSpendingScreen(SpendingViewModelMock(showMore = false))
    }
}