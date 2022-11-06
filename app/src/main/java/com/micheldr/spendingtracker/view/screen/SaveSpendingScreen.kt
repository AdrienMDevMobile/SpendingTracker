package com.micheldr.spendingtracker.view.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrienmandroid.datastore.view.ImageButton
import com.adrienmandroid.datastore.view.SaveTextField
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.element.AutoDelete
import com.micheldr.spendingtracker.view.element.ChequeSwitch
import com.micheldr.spendingtracker.view.element.DateButton
import com.micheldr.spendingtracker.view.viewmodel.SpendingViewModelMock
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.LocalDateTime.of as ofdate
import org.threeten.bp.OffsetDateTime.of as ofodt


@Composable
fun SaveSpendingScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier) {

        SaveTextField(
            value = viewModel.amount.value,
            onValueChanged = { newValue ->
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction
                        .AmountChanged(newValue)
                )
            },
            leadingIcon = R.drawable.ic_baseline_euro_24,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SaveTextField(
            value = viewModel.reason.value,
            onValueChanged = { newValue ->
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.ReasonChanged(newValue))
            },
            placeholder = R.string.reason_field
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            DateButton(
                value = viewModel.date.value,
                context = context,
                onDateClick = { mYear, mMonth, mDayOfMonth ->
                    onDateClick(viewModel, mYear, mMonth, mDayOfMonth)
                })

            Spacer(modifier = Modifier.width(20.dp))


            Column {
                ImageButton(
                    icon = R.drawable.ic_baseline_save_24,
                    onClick = {
                        viewModel.notifyViewAction(SpendingsViewModel.ViewAction.SaveSpending)
                    }
                )

                ChequeSwitch(viewModel.isCheque.value) { checked ->
                    viewModel.notifyViewAction(
                        SpendingsViewModel.ViewAction.IsChequeChanged(checked)
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            AutoDelete(activated = viewModel.autoDeleteActivated.value, onActivation = {
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction.IsAutoDeleteChanged(it)
                )
            }, onTimeLimitChanged = { })

        }

    }
}

val buttonWidth = 48.dp
val buttonHeight = 48.dp

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
fun SaveScreenPreviw() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SaveSpendingScreen(SpendingViewModelMock())
    }
}