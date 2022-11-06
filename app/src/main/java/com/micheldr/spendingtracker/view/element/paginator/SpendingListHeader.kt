package com.micheldr.spendingtracker.view.element.paginator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme

@Composable
fun SpendingHeader(mainHeader: Boolean, name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        backgroundColor = if (mainHeader) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.primaryVariant
        }
    ) {
        Text(text = name, fontSize = 17.sp, modifier = Modifier.padding(5.dp))
    }
}

@Preview
@Composable
fun SpendingHeaderPreview() {
    SpendingTrackerTheme {
        Column() {
            SpendingHeader(mainHeader = true, name = "mainHeader")
            SpendingHeader(mainHeader = false, name = "secondaryHeader")
        }
    }
}