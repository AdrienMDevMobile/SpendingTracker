package com.micheldr.spendingtracker.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.micheldr.spendingtracker.view.element.SpendingElement
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpendingListScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val spendingsState = viewModel.spendingsState.value
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides if(spendingsState.endReached) OverscrollConfiguration() else null
    ) {
        LazyColumn(modifier = modifier.fillMaxHeight()) {

            items(spendingsState.items.size) { i ->
                val item = spendingsState.items[i]
                if (i >= spendingsState.items.size - 1 && !spendingsState.endReached && !spendingsState.isLoading) {
                    viewModel.notifyViewAction(SpendingsViewModel.ViewAction.LoadSpending)
                }
                SpendingElement(item)
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (spendingsState.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}