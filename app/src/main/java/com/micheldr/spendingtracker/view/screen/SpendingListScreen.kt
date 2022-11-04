package com.micheldr.spendingtracker.view.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.micheldr.spendingtracker.view.element.SpendingElement
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel


@Composable
fun SpendingListScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val spendingsState = viewModel.spendingsState.value
    LazyColumn(modifier = modifier.fillMaxHeight()) {

        items (spendingsState.items.size) { i ->
            val item = spendingsState.items[i]
            if(i >= spendingsState.items.size - 1 && !spendingsState.endReached && !spendingsState.isLoading){
                viewModel.notifyViewAction(SpendingsViewModel.ViewAction.LoadSpending)
            }
            SpendingElement(item)
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}