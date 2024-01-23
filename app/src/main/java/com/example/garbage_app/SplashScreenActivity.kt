package com.example.garbage_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val startButton: Button = findViewById(R.id.btsplash)
        startButton.setOnClickListener {
            // Aksi yang akan diambil saat tombol "Mulai" ditekan
            redirectToLoginActivity()
        }
    }
    private fun redirectToLoginActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}