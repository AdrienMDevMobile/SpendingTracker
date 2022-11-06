package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.element.mySwitch.Switch
import com.micheldr.spendingtracker.view.element.mySwitch.SwitchDefaults.colors

enum class SwitchSize {
    Small,
    Medium,
    Large
}

@Composable
fun SavingSwitch(checked: Boolean, size: SwitchSize = SwitchSize.Small, onCheckedChanged: (Boolean) -> (Unit)) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChanged,
        modifier = Modifier.scale(
            when (size) {
                SwitchSize.Small -> 1f
                SwitchSize.Medium -> 1.25f
                SwitchSize.Large -> 1.5f
            }
        ),
        colors = colors(
            checkedThumbColor = Color.Black,
            checkedTrackColor = Color.White,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color.White,
            disabledCheckedThumbColor = Color.Gray,
            disabledCheckedTrackColor = Color.Gray,
            disabledUncheckedThumbColor = Color.Gray,
            disabledUncheckedTrackColor = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ChequeSwitchPreview() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        Column() {
            SavingSwitch(true) {}
            SavingSwitch(false, size = SwitchSize.Large) {}
        }

    }
}