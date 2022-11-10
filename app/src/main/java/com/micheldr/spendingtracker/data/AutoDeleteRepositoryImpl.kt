package com.micheldr.spendingtracker.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.micheldr.spendingtracker.data.AutoDeleteRepositoryImpl.PreferencesKey.AUTO_DELETE_ACTIVATED
import com.micheldr.spendingtracker.data.AutoDeleteRepositoryImpl.PreferencesKey.AUTO_DELETE_OPTION
import com.micheldr.spendingtracker.domain.arch.ExecuteResponse
import com.micheldr.spendingtracker.domain.arch.ExecuteResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class AutoDeleteRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    AutoDeleteRepository {

    private object PreferencesKey {
        val AUTO_DELETE_ACTIVATED = booleanPreferencesKey("AUTO_DELETE_ACTIVATED")
        val AUTO_DELETE_OPTION = stringPreferencesKey("AUTO_DELETE_OPTION")
    }

    override suspend fun setChoice(option: AutoDeleteChoice): ExecuteResponse<Nothing?> = try {
        dataStore.edit {
            it[AUTO_DELETE_OPTION] = option.toString()
        }
        ExecuteResult.Success(null)
    } catch (error: IOException) {
        ExecuteResult.Error(Error())
    }

    override suspend fun setActivated(activated: Boolean): ExecuteResponse<Nothing?> = try {
        dataStore.edit {
            it[AUTO_DELETE_ACTIVATED] = activated
        }
        ExecuteResult.Success(null)
    } catch (error: IOException) {
        ExecuteResult.Error(Error())
    }


    override val userAutoDeletePreferencesFlow: Flow<AutoDeleteOption> =
        dataStore.data.map { preferences ->
            AutoDeleteOption(
                preferences[AUTO_DELETE_ACTIVATED] ?: false,
                preferences[AUTO_DELETE_OPTION].toAutoDeleteChoice()
            )
        }
}