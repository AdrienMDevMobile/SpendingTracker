package com.micheldr.spendingtracker

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.datastore.preferences.preferencesDataStore
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.domain.useCase.SpendingFactory
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.screen.SaveSpendingScreen
import com.micheldr.spendingtracker.view.screen.SpendingListScreen
import com.micheldr.spendingtracker.view.viewmodel.SpendingsViewModel

class MainActivity : ComponentActivity() {

    companion object {
        const val USER_PREFERENCES_NAME = "spendingtracker_user_preferences"
    }

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)

        val factory = SpendingFactory(applicationContext, dataStore)
        val viewModel = factory.getSpendingViewModel

        viewModel.actionsToScreen.observe(this) { action ->
            when (action) {
                SpendingsViewModel.ActionToScreen.SavingComplete ->
                    Toast.makeText(this, getString(R.string.save_successful), Toast.LENGTH_LONG)
                        .show()

                null -> {}
            }

        }

        setContent {
            SpendingTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val (list, save) = createRefs()

                        SaveSpendingScreen(viewModel = viewModel, modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(save) {
                                height = Dimension.wrapContent
                                bottom.linkTo(parent.bottom)
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
}