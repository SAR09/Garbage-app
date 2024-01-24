package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityKertasArticleBinding

class KertasArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKertasArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kertas_article)

        binding = ActivityKertasArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemisahan dari Sampah Lainnya: Pisahkan kertas dari sampah lainnya. Tempatkan kertas dalam wadah atau tas sampah terpisah agar memudahkan proses daur ulang.</li>
                <li>Daur Ulang Kertas: Kertas dapat didaur ulang menjadi produk kertas baru tanpa kehilangan kualitasnya. Pastikan untuk membawa kertas bekas ke pusat daur ulang kertas setempat atau manfaatkan program daur ulang yang mungkin tersedia di komunitas Anda.</li>
                <li>Shredding (Penghancuran Kertas): Jika Anda memiliki dokumen yang mengandung informasi pribadi, pertimbangkan untuk menghancurkannya sebelum dibuang untuk menjaga keamanan informasi. Beberapa daerah menyediakan layanan penghancuran dokumen terpisah atau Anda dapat menggunakan mesin penghancur kertas sendiri.</li>
                <li>Mendaur Ulang Kertas Secara Kreatif: Manfaatkan kreativitas untuk mendaur ulang kertas menjadi proyek-proyek seni atau kerajinan, seperti membuat kertas daur ulang sendiri atau pembuatan kartu ucapan.</li>
              
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