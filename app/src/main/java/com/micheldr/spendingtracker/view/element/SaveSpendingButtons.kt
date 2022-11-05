package com.micheldr.spendingtracker.view.element

import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.ui.theme.onBackgroundDisabled
import com.micheldr.spendingtracker.view.screen.buttonHeight
import com.micheldr.spendingtracker.view.screen.buttonWidth
import com.micheldr.spendingtracker.view.screen.datePicker
import com.micheldr.spendingtracker.view.toLongString
import org.threeten.bp.OffsetDateTime

@Composable
fun DateButton(value: OffsetDateTime, context: Context, onDateClick: ((Int, Int, Int) -> Unit)) {
    Box(
        modifier = Modifier.clickable {
            datePicker(
                context,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    onDateClick(mYear, mMonth, mDayOfMonth)
                },
                value
            ).show()
        }
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value.toLongString())
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                contentDescription = "Timer",
                modifier = Modifier.size(72.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
            )
        }
    }
}

@Composable
fun SmallButton(icon: Int, onClick: (() -> Unit), isEnabled: Boolean = true) {
    Box(
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.save_button),
            modifier = Modifier.size(buttonWidth, buttonHeight),
            tint = if (isEnabled) {
                MaterialTheme.colors.onBackground
            } else {
                MaterialTheme.colors.onBackgroundDisabled
            }
        )
    }
    /*
    Button(
        onClick = { viewModel.notifyViewAction(SpendingsViewModel.ViewAction.SaveSpending) },
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = buttonPadding).background(Color.Transparent),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_baseline_save_24),
            contentDescription = stringResource(R.string.save_button),
            modifier = Modifier.size(buttonWidth, buttonHeight),
            tint = MaterialTheme.colors.onBackground,
        )
        //Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        //Text(stringResource(R.string.save_button))
    } */
}