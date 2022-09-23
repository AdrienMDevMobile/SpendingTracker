package com.micheldr.spendingtracker.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adrienmandroid.datastore.view.SpendingElement
import com.micheldr.spendingtracker.viewmodel.SpendingsViewModel

@Composable
fun SpendingListScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val spendings = viewModel.spendingList
    LazyColumn(modifier = modifier) {
        for (item in spendings.value) {
            item {
                SpendingElement(item)
            }
        }
    }
}