package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.data.Spending
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.ui.theme.highlightBackGround
import com.micheldr.spendingtracker.view.uiMapper.toUiState
import com.micheldr.spendingtracker.view.uiState.SpendingUiState
import org.threeten.bp.*

const val iconWeight = 0.08f

@Composable
fun SpendingElement(
    spending: SpendingUiState,
    baseExpended: Boolean = false,
) {
    val expended = remember { mutableStateOf(baseExpended) }
    val spacerWidth = 2.dp
    val font = TextStyle(fontSize = 20.sp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (spending.highlight) {
                    MaterialTheme.colors.highlightBackGround
                } else {
                    MaterialTheme.colors.background
                }
            )
            .clickable(onClick = {
                expended.value = !expended.value
            }),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (expended.value) {
            DateAndAmount(
                date = spending.date,
                amount = spending.value,
                spacerWidth = spacerWidth,
                font = font
            )
            Text(modifier = Modifier.weight(0.5f), text = spending.reason, style = font)
            MoneyOriginIcon(state = spending.origin, modifier = Modifier.weight(iconWeight))
            DeleteButton(Modifier.weight(iconWeight))
            Spacer(modifier = Modifier.width(spacerWidth))
            EditButton(modifier = Modifier.weight(iconWeight))
            Spacer(modifier = Modifier.width(spacerWidth))
            HighlightButton(spending.highlight, Modifier.weight(iconWeight)) {}
        } else {
            DateAndAmount(
                date = spending.date,
                amount = spending.value,
                spacerWidth = spacerWidth,
                font = font
            )
            Text(
                modifier = Modifier.weight(0.5f),
                text = spending.reason,
                style = font,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            MoneyOriginIcon(state = spending.origin, modifier = Modifier.weight(iconWeight))
        }
    }
}

@Composable
fun DateAndAmount(date: String, amount: String, spacerWidth: Dp, font: TextStyle) {
    Text(modifier = Modifier.width(40.dp), text = date, style = font)
    Spacer(modifier = Modifier.width(spacerWidth))
    Text(modifier = Modifier.width(40.dp), text = amount, style = font)
    Spacer(modifier = Modifier.width(spacerWidth))
}

@Composable
fun DeleteButton(modifier: Modifier = Modifier) {
    ImageButton(
        icon = R.drawable.ic_baseline_delete_24,
        onClick = {
        },
        modifier = modifier,
    )
}

@Composable
fun EditButton(modifier: Modifier = Modifier) {
    ImageButton(
        icon = R.drawable.ic_baseline_edit_24,
        onClick = {
        },
        modifier = modifier,
    )
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

@Preview
@Composable
fun PreviewSpendingElementHighlightExpended() {
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
            ).toUiState(),
            baseExpended = true,
        )
    }
}