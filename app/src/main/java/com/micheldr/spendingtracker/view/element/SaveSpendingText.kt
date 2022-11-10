package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.domain.SpendingError
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme

@Composable
fun SaveTextField(
    value: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChanged: ((String) -> Unit),
    leadingIcon: Int? = null,
    placeholder: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        modifier = modifier
            .border(
                border = BorderStroke(
                    3.dp,
                    MaterialTheme.colors.onBackground
                ),
                shape = RoundedCornerShape(10.dp)
            ),
        value = value,
        onValueChange = { newValue ->
            onValueChanged(newValue)
        },
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(it),
                    contentDescription = stringResource(R.string.save_button),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.onBackground,
                )
            }
        },
        placeholder = placeholder?.let {
            {
                stringResource(id = it)
            }
        },
        //shape = RoundedCornerShape(10.dp),
        singleLine = true,
        isError = isError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun SaveErrorMessage(error: SpendingError?) {
    Text(text = error?.let { stringResource(id = error.toTextResource()) } ?: "",
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth())
}

fun SpendingError.toTextResource(): Int =
    when (this) {
        SpendingError.AMOUNT_ERROR -> R.string.amount_error
        SpendingError.SAVE_ERROR -> R.string.save_error
    }

@Preview
@Composable
fun SaveTextFieldPreview() {
    SpendingTrackerTheme {
        Box(modifier = Modifier.background(Color.White)) {
            SaveTextField(
                value = "10",
                onValueChanged = { },
                leadingIcon = R.drawable.ic_baseline_euro_24,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

    }
}