package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.micheldr.spendingtracker.R

@Composable
fun HighlightButton(
    isHighlight: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = modifier.clickable { onClick(
            !isHighlight
        ) }
    ) {
        Icon(
            painter = painterResource(
                if (isHighlight) {
                    R.drawable.ic_baseline_star_full_24
                } else {
                    R.drawable.ic_baseline_star_empty
                }
            ),
            contentDescription = stringResource(R.string.highlight_button),
            modifier = modifier,
        )
    }
}