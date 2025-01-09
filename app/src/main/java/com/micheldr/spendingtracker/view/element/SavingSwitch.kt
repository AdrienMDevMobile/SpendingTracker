package com.micheldr.spendingtracker.view.element

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
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
fun SavingSwitch(checked: Boolean,
                 size: SwitchSize = SwitchSize.Small,
                 onCheckedChanged: (Boolean) -> (Unit)
) {
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
            checkedThumbColor = MaterialTheme.colors.onBackground,
            checkedTrackColor = MaterialTheme.colors.surface,
            uncheckedThumbColor = MaterialTheme.colors.secondaryVariant,
            uncheckedTrackColor = MaterialTheme.colors.background,
            checkedPaddingColor = MaterialTheme.colors.onBackground,
            uncheckedPaddingColor = MaterialTheme.colors.onBackground,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SavingSwitchPreview() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        Column() {
            SavingSwitch(true) {}
            SavingSwitch(false) {}
            SavingSwitch(true, size = SwitchSize.Large) {}
            SavingSwitch(false, size = SwitchSize.Large) {}
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SavingSwitchPreviewNight() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        Column() {
            SavingSwitch(true) {}
            SavingSwitch(false) {}
            SavingSwitch(true, size = SwitchSize.Large) {}
            SavingSwitch(false, size = SwitchSize.Large) {}
        }

    }
}