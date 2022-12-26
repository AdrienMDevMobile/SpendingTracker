package com.micheldr.spendingtracker.view.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.micheldr.spendingtracker.R
import com.micheldr.spendingtracker.data.MoneyOrigin
import com.micheldr.spendingtracker.view.data.MoneyOriginChoiceClick

@Composable
fun MoneyOriginIcon(state: MoneyOrigin, modifier: Modifier = Modifier) {
    when (state) {
        MoneyOrigin.CHEQUE -> Image(
            painter = painterResource(id = R.drawable.ic_cheque),
            contentDescription = stringResource(id = R.string.ic_cheque),
            modifier = modifier
        )
        MoneyOrigin.CREDIT_CARD -> Image(
            painter = painterResource(id = R.drawable.ic_credit_card),
            contentDescription = stringResource(
                id = R.string
                    .ic_credit_card
            ),
            modifier = modifier
        )
        MoneyOrigin.CASH -> Image(
            painter = painterResource(id = R.drawable.ic_cash),
            contentDescription = stringResource(id = R.string.ic_cash),
            modifier = modifier
        )
    }
}

@Composable
fun MoneyOriginText(state: MoneyOrigin, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(
            id = when (state) {
                MoneyOrigin.CHEQUE -> R.string.ic_cheque
                MoneyOrigin.CREDIT_CARD -> R.string.ic_credit_card
                MoneyOrigin.CASH -> R.string.ic_cash
            }
        ),
        modifier = modifier
    )
}

@Composable
fun MoneyOriginChoice(
    state: MoneyOrigin,
    onMoneyChoiceClick: ((MoneyOriginChoiceClick) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        MoneyOriginIcon(
            state,
            modifier = Modifier.weight(0.4f)
        )
        MoneyOriginText(
            state,
            modifier = Modifier.weight(0.4f)
        )
        Row(
            modifier = Modifier.weight(0.4f)
        ) {
            ImageButton(
                icon = R.drawable.ic_baseline_arrow_left_24,
                modifier = Modifier.weight(0.5f)
            ) { onMoneyChoiceClick(MoneyOriginChoiceClick.LEFT) }
            ImageButton(
                icon = R.drawable.ic_baseline_arrow_right_24,
                modifier = Modifier.weight(0.5f)
            ) { onMoneyChoiceClick(MoneyOriginChoiceClick.RIGHT) }
        }
    }
}

@Preview
@Composable
fun PreviewMoneyStateChoiceCheque() {
    MoneyOriginChoice(MoneyOrigin.CHEQUE, {})
}

@Preview
@Composable
fun PreviewMoneyStateChoiceCard() {
    MoneyOriginChoice(MoneyOrigin.CREDIT_CARD, {})
}

@Preview
@Composable
fun PreviewMoneyStateChoiceCash() {
    MoneyOriginChoice(MoneyOrigin.CASH, {})
}