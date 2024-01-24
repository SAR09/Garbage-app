package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityKacaArticleBinding


class KacaArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKacaArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kaca_article)

        binding = ActivityKacaArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemisahan dari Sampah Lainnya: Pisahkan kaca dari sampah lainnya untuk memudahkan proses daur ulang. Tempatkan kaca dalam wadah atau tas sampah terpisah.</li>
                <li>Pengumpulan dan Penyimpanan yang Aman: Ketika membuang kaca, pastikan untuk menggunakan wadah yang kuat dan tahan pecah. Jangan mencampur kaca dengan bahan-bahan lain yang dapat merusak atau membahayakan kaca dan orang yang menanganinya.</li>
                <li>Peringatan dan Tanda Bahaya: Jika Anda membuang kaca yang pecah atau berpotensi membahayakan, pastikan untuk memberikan peringatan kepada petugas pengumpulan sampah atau orang lain yang mungkin menangani sampah tersebut. Tandai dengan jelas jika kaca tersebut pecah atau memiliki potensi bahaya lainnya.</li>
                <li>Pembungkusan yang Aman: Jika Anda perlu membuang barang kaca besar atau pecah, bungkus kaca tersebut dengan aman menggunakan kertas berlipat atau bahan pelindung lainnya untuk mengurangi risiko cedera.</li>
              
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