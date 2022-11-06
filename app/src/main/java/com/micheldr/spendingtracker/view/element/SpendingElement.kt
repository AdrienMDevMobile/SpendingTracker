package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrienmandroid.datastore.view.ImageButton
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.toUiState
import com.micheldr.spendingtracker.view.uiState.SpendingUiState
import org.threeten.bp.*
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.ui.theme.highlightBackGround


@Composable
fun SpendingElement(
    spending: SpendingUiState
) {
    val spacerWidth = 5.dp
    val font = TextStyle(fontSize = 20.sp)
    Row(modifier = Modifier.fillMaxWidth().background(color = if (spending.highlight) {
        MaterialTheme.colors.highlightBackGround
    } else {
        MaterialTheme.colors.background
    }),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.weight(0.1f), text = spending.date, style = font)
        Spacer(modifier = Modifier.width(spacerWidth))
        Text(modifier = Modifier.weight(0.1f), text = spending.value.toString(), style = font)
        Spacer(modifier = Modifier.width(spacerWidth))
        Text(modifier = Modifier.weight(0.5f), text = spending.reason, style = font)
        ImageButton(
            icon = R.drawable.ic_baseline_delete_24,
            onClick = {
            },
            modifier = Modifier.weight(0.08f)
        )
        Spacer(modifier = Modifier.width(spacerWidth))
        ImageButton(
            icon = R.drawable.ic_baseline_edit_24,
            onClick = {
            },
            modifier = Modifier.weight(0.08f)
        )
        Spacer(modifier = Modifier.width(spacerWidth))
        ImageButton(
            icon = if (spending.highlight) {
                R.drawable.ic_baseline_star_full_24
            } else {
                R.drawable.ic_baseline_star_empty
            },
            onClick = {
            },
            modifier = Modifier.weight(0.08f)
        )
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
                    LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT,
                    ZoneOffset.UTC
                ),
                highlight = false
            ).toUiState()
        )
    }
}

@Preview
@Composable
fun PreviewSpendingElementHighlight() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SpendingElement(
            spending = Spending(
                value = 50,
                reason = "Test reason---------------------------",
                date = OffsetDateTime.of(
                    LocalDate.of(2022, Month.JANUARY, 21), LocalTime.MIDNIGHT,
                    ZoneOffset.UTC
                ),
                highlight = true
            ).toUiState()
        )
    }
}