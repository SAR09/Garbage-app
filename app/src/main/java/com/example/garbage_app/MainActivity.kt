package com.example.garbage_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var homeLayout: LinearLayout
    private lateinit var predictionLayout: LinearLayout
    private lateinit var logout: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOrganik = findViewById<Button>(R.id.organikbt)
        buttonOrganik.setOnClickListener {
            val intent = Intent(this, OrganikArticleActivity::class.java)
            startActivity(intent)
        }

        val buttonAnorganik = findViewById<Button>(R.id.anorganikbt)
        buttonAnorganik.setOnClickListener {
            val intent = Intent(this, AnorganikArticleActivity::class.java)
            startActivity(intent)
        }

        //ItemList untuk Jenis Sampah
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


        predictionLayout = findViewById(R.id.prediction)
        homeLayout = findViewById(R.id.home)
        logout = findViewById(R.id.logout)


        homeLayout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        predictionLayout.setOnClickListener {
            startActivity(Intent(this, PredicitionActivity::class.java))
        }

        logout.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }





    }






}
