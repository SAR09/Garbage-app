package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityPlasticArticleBinding

class PlasticArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlasticArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic_article)

        binding = ActivityPlasticArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemisahan dari Sampah Lainnya: Pisahkan sampah plastik dari sampah organik dan lainnya. Tempatkan plastik dalam wadah atau tas sampah terpisah agar memudahkan proses daur ulang.</li>
                <li>Daur Ulang Plastik: Bawa sampah plastik ke pusat daur ulang plastik setempat atau manfaatkan program daur ulang plastik yang tersedia di komunitas Anda. Pastikan plastik yang akan didaur ulang bersih dan bebas dari kontaminasi.</li>
                <li>Pembersihan Sebelum Dibuang: Pastikan untuk membersihkan plastik dari sisa-sisa makanan atau minuman sebelum membuangnya. Plastik yang bersih lebih mudah didaur ulang dan memiliki nilai daur ulang yang lebih tinggi.</li>
                <li>Kompresi Sampah Plastik: Agar lebih efisien dalam pengumpulan dan pengangkutan sampah plastik, kompres sampah plastik dengan menekan udara keluar dari kantong atau wadah.</li>
              
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