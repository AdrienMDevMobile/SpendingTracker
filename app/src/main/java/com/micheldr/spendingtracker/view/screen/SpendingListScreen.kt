package com.micheldr.spendingtracker.view.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.micheldr.spendingtracker.view.element.SpendingElement
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel

@Composable
fun SpendingListScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val spendings = viewModel.spendingList
    LazyColumn(modifier = modifier.fillMaxHeight()) {
        for (item in spendings.value) {
            item {
                SpendingElement(item)
            }
        }
    }
}