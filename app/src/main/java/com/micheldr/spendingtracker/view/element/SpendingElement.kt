package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.toUiState
import com.micheldr.spendingtracker.view.viewmodel.SpendingUiState
import org.threeten.bp.*

@Composable
fun SpendingElement(
    spending: SpendingUiState
) {
    val font = TextStyle(fontSize = 20.sp)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(modifier = Modifier.weight(0.4f), text = spending.date, style = font)
        Text(modifier = Modifier.weight(0.1f), text = spending.value.toString(), style = font)
        Text(modifier = Modifier.weight(0.5f), text = spending.reason, style = font)
        //Put edit button here
    }
}

@Preview
@Composable
fun PreviewSpendingElement() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SpendingElement(
            spending = Spending(
                value = 50,
                reason = "Test reason---------------------------",
                date = OffsetDateTime.of(
                    LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT, ZoneOffset.UTC
                )
            ).toUiState()
        )
    }
}