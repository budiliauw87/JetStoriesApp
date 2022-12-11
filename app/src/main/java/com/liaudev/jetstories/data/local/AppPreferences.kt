package com.liaudev.jetstories.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.liaudev.jetstories.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Budiliauw87 on 2022-12-09.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class AppPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val ID_KEY = stringPreferencesKey("userId")
        private val NAME_KEY = stringPreferencesKey("userName")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val LOGIN_KEY = booleanPreferencesKey("isLogin")

        @Volatile
        private var INSTANCE: AppPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    //save user
    suspend fun saveUser(user: User) {
        dataStore.edit {
            it[ID_KEY] =user.userId
            it[NAME_KEY] =user.name
            it[TOKEN_KEY] =user.token
            it[LOGIN_KEY] =user.islogin
        }
    }
    // clear
    suspend fun logout() {
        dataStore.edit { it.clear() }
    }
    // get user
    fun getUser(): Flow<User> {
        return dataStore.data.map {
            val userId = it[ID_KEY] ?: ""
            val name = it[NAME_KEY] ?: ""
            val token = it[TOKEN_KEY] ?: ""
            val isLogin = it[LOGIN_KEY] ?: false
            User(userId,name, token, isLogin)
        }
    }
}