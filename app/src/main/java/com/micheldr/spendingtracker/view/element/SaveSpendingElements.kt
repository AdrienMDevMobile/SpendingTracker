package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.micheldr.spendingtracker.R

@Composable
fun ChequeSwitch(checked: Boolean, onCheckedChanged: (Boolean) -> (Unit)) {
    Column() {
        Text(text = stringResource(id = R.string.isCheque))
        SavingSwitch(checked = checked, size = SwitchSize.Medium, onCheckedChanged = onCheckedChanged)
    }
}