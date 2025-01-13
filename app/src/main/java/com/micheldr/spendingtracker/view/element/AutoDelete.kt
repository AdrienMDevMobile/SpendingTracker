package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowRow
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.ui.theme.onBackgroundDisabled
import com.micheldr.spendingtracker.view.uiState.AutoDeleteUiState


@Composable
fun AutoDelete(
    uiState: AutoDeleteUiState,
    modifier: Modifier = Modifier,
    onActivation: (Boolean) -> Unit,
    onTimeLimitChanged: (AutoDeleteChoice) -> Unit
) {
    Column(modifier) {
        Text(text = stringResource(id = R.string.autoDeleteActivated))

        SavingSwitch(
            checked = uiState.isActivated,
            size = SwitchSize.Large,
            onCheckedChanged = onActivation
        )

        //TODO remplacer par une génération automatique, depuis une liste
        FlowRow { TextButton(
                stringResource(id = R.string.delete_one_year),
                isEnabled = uiState.isActivated,
                isActive = (AutoDeleteChoice.ONE_YEAR == uiState.autoDeleteChosen)
            ) {
                onTimeLimitChanged(AutoDeleteChoice.ONE_YEAR)
            }
            TextButton(
                stringResource(id = R.string.delete_three_months),
                isEnabled = uiState.isActivated,
                isActive = (AutoDeleteChoice.THREE_MONTH == uiState.autoDeleteChosen)
            ) {
                onTimeLimitChanged(AutoDeleteChoice.THREE_MONTH)
            }
            TextButton(
                stringResource(id = R.string.delete_one_month),
                isEnabled = uiState.isActivated,
                isActive = (AutoDeleteChoice.ONE_MONTH == uiState.autoDeleteChosen)
            ) {
                onTimeLimitChanged(AutoDeleteChoice.ONE_MONTH)
            }
            TextButton(
                stringResource(id = R.string.delete_one_week),
                isEnabled = uiState.isActivated,
                isActive = (AutoDeleteChoice.ONE_WEEK == uiState.autoDeleteChosen)
            ) {
                onTimeLimitChanged(AutoDeleteChoice.ONE_WEEK)
            }
        }
    }
}

@Composable
fun AutoDeleteChoice.autoDeleteButtonColor(isEnabled: Boolean, selectedChoice: AutoDeleteChoice) =
    if (!isEnabled) {
        androidx.compose.material.MaterialTheme.colors.onBackgroundDisabled
    } else {
        if (this == selectedChoice) {
            androidx.compose.material.MaterialTheme.colors.onBackground
        } else {
            androidx.compose.material.MaterialTheme.colors.onBackgroundDisabled
        }
    }

@Preview
@Composable
fun AutoDeletePreviewOn() {
    AutoDelete(
        AutoDeleteUiState(true, AutoDeleteChoice.ONE_YEAR),
        onActivation = {},
        onTimeLimitChanged = {})
}

@Preview
@Composable
fun AutoDeletePreviewOff() {
    AutoDelete(
        AutoDeleteUiState(false, AutoDeleteChoice.ONE_YEAR),
        onActivation = {},
        onTimeLimitChanged = {})
}