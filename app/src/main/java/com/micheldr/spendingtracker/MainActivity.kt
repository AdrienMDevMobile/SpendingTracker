package com.micheldr.spendingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.domain.useCase.SpendingUseCaseFactory
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.screen.SaveSpendingScreen
import com.micheldr.spendingtracker.view.screen.SpendingListScreen
import com.micheldr.spendingtracker.view.viewmodel.SpendingViewModelImpl


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)

        val factory = SpendingUseCaseFactory(applicationContext)
        val viewModel = SpendingViewModelImpl(
            factory.saveSpendingUseCase,
            factory.getSpendingsPaginateUseCase
        )

        setContent {
            SpendingTrackerTheme {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    val (list, save) = createRefs()

                    SaveSpendingScreen(viewModel = viewModel, modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(save) {
                            height = Dimension.wrapContent
                            bottom.linkTo(parent.bottom, margin = 30.dp)
                            top.linkTo(list.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    SpendingListScreen(viewModel = viewModel, modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(list) {
                            height = Dimension.fillToConstraints
                            top.linkTo(parent.top)
                            bottom.linkTo(save.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }

                    )
                }
            }
        }
    }
}