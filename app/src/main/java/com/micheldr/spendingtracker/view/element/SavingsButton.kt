package com.micheldr.spendingtracker.view.element

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.ui.theme.onBackgroundDisabled

@Composable
fun ImageButton(
    icon: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isActive: Boolean = true,
    color: SavingsColors = SavingsButtonDefaults.colors(),
    onClick: (() -> Unit)
) {
    val buttonColor by color.color(isEnabled, isActive)
    Icon(
        painter = painterResource(icon),
        contentDescription = stringResource(R.string.save_button),
        modifier = modifier.clickable { onClick() },
        tint = buttonColor
    )
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isActive: Boolean = true,
    color: SavingsColors = SavingsButtonDefaults.colors(),
    onClick: (() -> Unit)
) {
    val buttonColor by color.color(isEnabled, isActive)
    Box(
        modifier = modifier.clickable { if (isEnabled) onClick() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(
                color = buttonColor,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(5.dp)
                .border(2.dp, buttonColor, RoundedCornerShape(percent = 70))
                .padding(5.dp)
        )
    }
}

@Stable
interface SavingsColors {
    @Composable
    fun color(enabled: Boolean, active: Boolean): State<Color>
}

object SavingsButtonDefaults {
    @Composable
    fun colors(
        activeColor: Color = MaterialTheme.colors.onBackground,
        inactiveColor: Color = MaterialTheme.colors.secondaryVariant
    ): SavingsColors = DefaultSavingsColors(
        activeColor = activeColor,
        inactiveColor = inactiveColor,
    )
}

@Immutable
private class DefaultSavingsColors(
    private val activeColor: Color,
    private val inactiveColor: Color,
) : SavingsColors {
    @Composable
    override fun color(enabled: Boolean, active: Boolean): State<Color> = rememberUpdatedState(
        if (enabled && active) activeColor else inactiveColor
    )

}

@Preview
@Composable
fun PreviewTextButton() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        Column {
            TextButton(text = "Actif", isEnabled = true, onClick = {})
            TextButton(text = "Inactif", isEnabled = false, onClick = {})
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTextButtonDark() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        Column {
            TextButton(text = "Actif", isEnabled = true, onClick = {})
            TextButton(text = "Inactif", isEnabled = false, onClick = {})
        }
    }
}