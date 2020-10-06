package com.example.oplus.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class SharedPreferencesManager {
    companion object {
        const val KEY_USER = "USER"

        private fun pref(con: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(con)
        }

        fun getBoolean(con: Context, key: String, defValue: Boolean): Boolean {
            return try {
                pref(con).getBoolean(key, defValue)
            } catch (e: Exception) {
                defValue
            }
        }

        fun getInt(con: Context, key: String, defValue: Int): Int {
            return try {
                pref(con).getInt(key, defValue)
            } catch (e: Exception) {
                defValue
            }

        }

        fun getString(con: Context, key: String, defValue: String?): String? {
            return try {
                pref(con).getString(key, defValue)
            } catch (e: Exception) {
                defValue
            }

        }

        fun getLong(con: Context, key: String, defValue: Long): Long {
            return try {
                pref(con).getLong(key, defValue)
            } catch (e: Exception) {
                defValue
            }

        }

        fun getFloat(con: Context, key: String, defValue: Float): Float {
            return try {
                pref(con).getFloat(key, defValue)
            } catch (e: Exception) {
                defValue
            }

        }

        fun writeString(con: Context, key: String, value: String?) {
            try {
                pref(con).edit().putString(key, value).apply()
            } catch (e: Exception) {
            }
        }
    }
// other getters/setters
}