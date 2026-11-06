package com.example.satset.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun login(username: String) {
        prefs.edit().putString("username", username).putBoolean("logged_in", true).apply()
    }

    fun isLoggedIn() = prefs.getBoolean("logged_in", false)

    fun getUsername(): String = prefs.getString("username", "Pengguna") ?: "Pengguna"

    fun logout() {
        prefs.edit().clear().apply()
    }
}