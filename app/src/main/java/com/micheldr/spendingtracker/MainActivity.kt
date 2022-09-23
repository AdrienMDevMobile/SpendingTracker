package com.micheldr.spendingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.jakewharton.threetenabp.AndroidThreeTen
import com.micheldr.spendingtracker.data.SpendingDatabase
import com.micheldr.spendingtracker.data.SpendingRepositoryImpl
import com.micheldr.spendingtracker.ui.theme.SpendingTrackerTheme
import com.micheldr.spendingtracker.view.SaveSpendingScreen
import com.micheldr.spendingtracker.view.SpendingListScreen
import com.micheldr.spendingtracker.viewmodel.SpendingViewModelImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)

        val viewModel = SpendingViewModelImpl(
            SpendingRepositoryImpl(
                SpendingDatabase.getDatabase(applicationContext).spendingDao()
            )
        )

        setContent {
            SpendingTrackerTheme {
                Column() {
                    SaveSpendingScreen(viewModel = viewModel, modifier = Modifier.fillMaxWidth())
                    SpendingListScreen(viewModel = viewModel, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}