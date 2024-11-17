package com.pedro0210.hobbylobby.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserData(
    private val dataStore: DataStore<Preferences>
) {
    private val idKey = stringPreferencesKey("id") //will access this for all firebase related
    private val loggedKey = booleanPreferencesKey("logged")


    suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = false
        }
    }

    suspend fun logIn(id:String){
        dataStore.edit { preferences ->
            preferences[loggedKey] = true
            preferences[idKey] = id
        }
    }

    fun getAuth(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[loggedKey] ?: false
    }

    fun getId(): Flow<String> = dataStore.data.map{ preferences ->
        preferences[idKey].toString()
    }
}