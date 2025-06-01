package ar.edu.ort.parcial_tp3.data.local.session

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserSession(private val context: Context) {

    companion object {
        private val USER_ID_KEY = intPreferencesKey("user_id")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")


    }

    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    private fun getUserId(): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }
    }

    suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
        }
    }

    private fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }
    }
    suspend fun setLoggedIn(loggedIn: Boolean) {
        context.dataStore.edit { it[IS_LOGGED_IN_KEY] = loggedIn }
    }

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGGED_IN_KEY] ?: false
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
    val userIdFlow get() = getUserId()
    val accessTokenFlow get() = getAccessToken()
}
