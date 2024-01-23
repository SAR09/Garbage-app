package com.example.garbage_app

import androidx.appcompat.app.AppCompatActivity

data class ItemModel(
    val title: String,
    val imageResId: Int,
    val buttonText: String,
    val targetActivityClass: Class<out AppCompatActivity>
)
