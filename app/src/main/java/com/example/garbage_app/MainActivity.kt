// MainActivity.kt
package com.example.garbage_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = listOf(
            ItemModel("Baterai", R.drawable.battery, "Lihat", BatteryArticleActivity::class.java),
            ItemModel("Makanan", R.drawable.makanan, "Lihat", MakananArticleActivity::class.java),
            ItemModel("Kardus", R.drawable.kardus, "Lihat", KardusArticleActivity::class.java),
            ItemModel("Pakaian", R.drawable.pakaian, "Lihat", PakaianArticleActivity::class.java),
            ItemModel("Kaca", R.drawable.kaca, "Lihat", KacaArticleActivity::class.java),
            ItemModel("Logam", R.drawable.logam, "Lihat", LogamArticleActivity::class.java),
            ItemModel("Kertas", R.drawable.kertas, "Lihat", KertasArticleActivity::class.java),
            ItemModel("Plastik", R.drawable.plastik, "Lihat", PlasticArticleActivity::class.java),
            ItemModel("Sepatu", R.drawable.sepatu, "Lihat", SepatuArticleActivity::class.java),
            // Tambahkan item lain sesuai kebutuhan
        )

        recyclerView = findViewById(R.id.recycleView)
        customAdapter = CustomAdapter(this, itemList)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter

        // Menambahkan dekorasi jarak antar CardView
        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.space_between_cards)
        recyclerView.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }
}
