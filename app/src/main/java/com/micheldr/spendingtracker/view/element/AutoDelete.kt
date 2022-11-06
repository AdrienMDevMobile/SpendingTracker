package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.adrienmandroid.datastore.view.TextButton
import com.google.accompanist.flowlayout.FlowRow
import com.micheldr.spendingtracker.R

@Composable
fun AutoDelete(
    activated: Boolean,
    onActivation: (Boolean) -> Unit,
    onTimeLimitChanged: () -> Unit
) {
    Column {
        Text(text = stringResource(id = R.string.autoDeleteActivated))

        SavingSwitch(checked = activated, size = SwitchSize.Large, onCheckedChanged = onActivation)

        //TODO remplacer par une génération automatique, depuis une liste
        FlowRow() {
            TextButton(R.string.delete_one_year, isEnabled = activated) {}
            TextButton(R.string.delete_three_months, isEnabled = activated) {}
            TextButton(R.string.delete_one_month, isEnabled = activated) {}
            TextButton(R.string.delete_one_week, isEnabled = activated) {}
        }
    }
}

@Preview
@Composable
fun AutoDeletePreviewOn() {
    AutoDelete(true, {}, {})
}

@Preview
@Composable
fun AutoDeletePreviewOff() {
    AutoDelete(false, {}, {})
}