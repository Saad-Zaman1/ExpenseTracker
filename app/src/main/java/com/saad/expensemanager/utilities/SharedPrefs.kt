package com.saad.expensemanager.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editer: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveString(key: String, value: String) {
        editer.putString(key, value).apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        editer.putBoolean(key, value).apply()
    }


    //Getting Prefs values

    fun getString(key: String, value: String): String {
        return sharedPreferences.getString(key, value) ?: value
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearPrefs() {
        sharedPreferences.edit().clear().apply()
    }
}