package com.example.garbage_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityBatteryArticleBinding
import com.example.garbage_app.databinding.ActivitySignInBinding

class BatteryArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBatteryArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_article)

        binding = ActivityBatteryArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}