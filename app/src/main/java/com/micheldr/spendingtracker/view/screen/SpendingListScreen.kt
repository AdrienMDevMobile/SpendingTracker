package com.micheldr.spendingtracker.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.element.LoadButton
import com.micheldr.spendingtracker.view.element.SpendingElement
import com.micheldr.spendingtracker.view.element.paginator.DoubleHeaderLazyColumnPaginated
import com.micheldr.spendingtracker.view.element.paginator.SpendingHeader
import com.micheldr.spendingtracker.view.uiState.SpendingUiState
import com.micheldr.spendingtracker.view.viewmodel.SpendingViewModelMock
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpendingListScreen(
    viewModel: SpendingsViewModel,
    modifier: Modifier = Modifier
) {
    val spendingsState = viewModel.spendingsState.value
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides if (spendingsState.endReached) OverscrollConfiguration() else null
    ) {
        if (spendingsState.items.isNotEmpty()) {
            DoubleHeaderLazyColumnPaginated(
                data = spendingsState.items,
                modifier = modifier,
                headerContent = {
                    SpendingHeader(true, it)
                },
                subHeaderContent = {
                    SpendingHeader(false, it)
                },
                itemContent = {
                    val item = it as SpendingUiState
                    SpendingElement(spending = item)
                },
                paginationAction = { viewModel.notifyViewAction(SpendingsViewModel.ViewAction.LoadSpending) },
                endReached = spendingsState.endReached,
                isLoading = spendingsState.isLoading
            )
        } else {
            LoadButton(modifier = modifier,) {
                viewModel.notifyViewAction(
                    SpendingsViewModel.ViewAction.LoadSpending
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewSpendingsListScreen() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SpendingListScreen(
            viewModel = SpendingViewModelMock()
        )
    }
}

@Preview
@Composable
fun PreviewSpendingsListScreenEmpty() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        SpendingListScreen(
            modifier = Modifier.fillMaxSize(),
            viewModel = SpendingViewModelMock(showElement = false)
        )
    }
}