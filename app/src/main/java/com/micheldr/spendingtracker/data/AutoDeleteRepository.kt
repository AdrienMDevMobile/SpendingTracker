package com.micheldr.spendingtracker.data

import com.micheldr.spendingtracker.domain.arch.ExecuteResponse
import kotlinx.coroutines.flow.Flow

interface AutoDeleteRepository {
    suspend fun setActivated(activated: Boolean): ExecuteResponse<Nothing?>
    suspend fun setChoice(option: AutoDeleteChoice): ExecuteResponse<Nothing?>
    val userAutoDeletePreferencesFlow: Flow<AutoDeleteOption>
    suspend fun getUserAutoDeletePreferences(): AutoDeleteOption
}