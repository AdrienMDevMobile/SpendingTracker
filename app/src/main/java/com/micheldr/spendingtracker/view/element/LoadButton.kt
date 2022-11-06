package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.ui.theme.onBackgroundDisabled

@Composable
fun LoadButton(modifier: Modifier = Modifier, onclick: (() -> Unit)){
    Box(
        modifier = modifier.clickable { onclick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = stringResource(id = R.string.load_savings_button),
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.onBackgroundDisabled,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadButton() {
    AndroidThreeTen.init(LocalContext.current)

    SpendingTrackerTheme {
        LoadButton{}
    }
}