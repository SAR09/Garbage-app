package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivitySepatuArticleBinding

class SepatuArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySepatuArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepatu_article)

        binding = ActivitySepatuArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Reparasi Sepatu: Jika sepatu mengalami kerusakan yang dapat diperbaiki, pertimbangkan untuk membawanya ke tukang sepatu lokal untuk diperbaiki. Memperbaiki sepatu dapat memperpanjang umur pakainya.</li>
                <li>Mendaur Ulang Bahan Sepatu: Beberapa bahan sepatu dapat didaur ulang, terutama sol karet atau bahan sintetis tertentu. Periksa apakah ada fasilitas daur ulang di daerah Anda yang menerima sepatu atau bahan sepatu tertentu.</li>
                <li>Pemisahan Bahan: Jika sepatu terbuat dari beberapa jenis bahan, pisahkan bahan-bahan tersebut sebelum membuangnya. Ini membantu memfasilitasi proses daur ulang jika beberapa bagian sepatu dapat didaur ulang.</li>
                <li>Pengelolaan Sampah Beracun: Beberapa sepatu mungkin mengandung bahan kimia beracun atau berbahaya. Pastikan untuk memeriksa panduan pengelolaan sampah berbahaya di wilayah Anda dan buang sepatu tersebut sesuai dengan pedoman yang berlaku.</li>
              
            </ol>
        """.trimIndent()

        binding.isiPenanganan.text = fromHtml(orderedListText)
    }

    @SuppressLint("ObsoleteSdkInt")
    @Suppress("DEPRECATION")
    private fun fromHtml(html: String): CharSequence {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            android.text.Html.fromHtml(html, android.text.Html.FROM_HTML_MODE_LEGACY)
        } else {
            android.text.Html.fromHtml(html)
        }
    }
}