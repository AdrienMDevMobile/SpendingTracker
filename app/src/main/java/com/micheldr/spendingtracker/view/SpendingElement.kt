package com.adrienmandroid.datastore.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.micheldr.spendingtracker.viewmodel.SpendingUiState

@Composable
fun SpendingElement(
    spending: SpendingUiState
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(spending.date)
        Text(spending.value.toString())
        Text(spending.reason)
    }
}