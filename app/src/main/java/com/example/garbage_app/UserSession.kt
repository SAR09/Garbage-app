package com.example.garbage_app

import android.content.Context

class UserSession(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // Menyimpan status login pengguna
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    // Mengatur status login pengguna
    fun setLoggedIn(isLoggedIn: Boolean) {
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    // Contoh metode lain untuk menyimpan dan mengambil informasi pengguna seperti ID, nama, dll.
    // Sesuaikan dengan kebutuhan aplikasi Anda.
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    fun setUserId(userId: String) {
        editor.putString("userId", userId)
        editor.apply()
    }

}
