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
    private val usernameKey = stringPreferencesKey("username")
    private val pfpKey = stringPreferencesKey("pfp")
    private val idKey = stringPreferencesKey("id") //will access this for all firebase related
    private val loggedKey = booleanPreferencesKey("logged")


    suspend fun loggedIn() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = true
        }
    }
    suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = false
        }
    }

    suspend fun setUserKeys(
        id:String,
        username: String,
        pfp: String
        ){
        dataStore.edit { preferences ->
            preferences[idKey] = id
            preferences[usernameKey] = username
            preferences[pfpKey] = pfp
        }
    }

    fun getAuth(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[loggedKey] ?: false
    }

    fun getId(): Flow<String> = dataStore.data.map{ preferences ->
        preferences[idKey].toString()
    }

    fun getUsername(): Flow<String> = dataStore.data.map{ preferences ->
        preferences[usernameKey].toString()}

    fun getPfp(): Flow<String> = dataStore.data.map{ preferences ->
        preferences[pfpKey].toString()}

}