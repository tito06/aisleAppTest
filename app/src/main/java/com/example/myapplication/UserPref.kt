package com.example.myapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPref(
    private  val context: Context
){

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    suspend fun saveToken(token:String){
       context.dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    val tokenFlow: Flow<String> = context.dataStore.data.map {
        it[TOKEN_KEY] ?:""

    }

}