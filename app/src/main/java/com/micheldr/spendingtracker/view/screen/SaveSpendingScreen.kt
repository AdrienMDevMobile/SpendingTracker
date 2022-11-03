package com.micheldr.spendingtracker.view.screen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.toLongString
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

    val textModifier = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        TextField(
            modifier = textModifier,
            value = viewModel.amount.value,
            onValueChange = { newValue ->
                if (newValue.isNotEmpty()) {
                    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.AmountChanged(newValue))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = textModifier,
            value = viewModel.reason.value,
            onValueChange = { newValue ->
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.ReasonChanged(newValue))
            })

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.clickable {
                    datePicker(
                        context,
                        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                            onDateClick(viewModel, mYear, mMonth, mDayOfMonth)
                        },
                        viewModel.date.value
                    ).show()
                }
            ) {
                Text(text = viewModel.date.value.toLongString())
                //Mettre ici l'image de la montre
            }



            Spacer(modifier = Modifier.width(20.dp))

            Column {
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
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SaveSpendingScreen(SpendingViewModelMock())
    }
}