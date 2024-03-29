package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    onClick: (() -> Unit)
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = stringResource(R.string.save_button),
        modifier = modifier.clickable { onClick() },
        tint = if (isEnabled) {
            MaterialTheme.colors.onBackground
        } else {
            MaterialTheme.colors.onBackgroundDisabled
        }
    )
}

@Composable
fun TextButton(
    text: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    color: Color = MaterialTheme.colors.onBackground,
    onClick: (() -> Unit)
) {
    Box(
        modifier = modifier.clickable { if (isEnabled) onClick() }
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.body2.copy(
                color = color,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(5.dp)
                .border(2.dp, color, RoundedCornerShape(percent = 70))
                .padding(5.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTextButton() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        TextButton(text = R.string.save_button, onClick = {})
    }
}