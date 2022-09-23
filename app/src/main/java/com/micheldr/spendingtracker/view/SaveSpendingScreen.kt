package com.micheldr.spendingtracker.view

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.viewmodel.SpendingViewModelMock
import com.micheldr.spendingtracker.viewmodel.SpendingsViewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime

@Composable
fun SaveSpendingScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        TextField(
            value = viewModel.amount.value.toString(), onValueChange = { newValue ->
                if (!newValue.isNullOrEmpty()) {
                    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.AmountChanged(newValue.toInt()))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = viewModel.reason.value, onValueChange = { newValue ->
            viewModel.notifyViewAction(SpendingsViewModel.ViewAction.ReasonChanged(newValue))
        })

        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(text = AnnotatedString(
            viewModel.date.value.toMyString()
        ), onClick = {
            datePicker(
                context,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    onDateClick(viewModel, mYear, mMonth, mDayOfMonth)
                },
                viewModel.date.value
            ).show()
        })

        Button(
            onClick = { viewModel.notifyViewAction(SpendingsViewModel.ViewAction.SaveSpending) }
        ) {
            /* Put save icon
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))*/
            Text("Save")
        }

        Button(
            onClick = { viewModel.notifyViewAction(SpendingsViewModel.ViewAction.LoadSpending) }
        ) {
            Text("Load")
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
    val date = LocalDateTime.of(mYear, mMonth + 1, mDayOfMonth, 0, 0)
    val odt = OffsetDateTime.of(date, OffsetDateTime.now().offset)
    /*val cal = GregorianCalendar()
    cal[Calendar.YEAR] = mYear
    cal[Calendar.MONTH] = mMonth
    cal[Calendar.DAY_OF_MONTH] = mDayOfMonth*/
    //val dateRepresentation = cal.time
    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.DateChanged(odt))
}

//TODO enchainer datepickerdialog avec timepickerdialog

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpendingTrackerTheme {
        SaveSpendingScreen(SpendingViewModelMock())
    }
}