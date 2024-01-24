package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityLogamArticleBinding

class LogamArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogamArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logam_article)

        binding = ActivityLogamArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemisahan dari Sampah Lainnya: Pisahkan logam dari sampah lainnya agar memudahkan proses daur ulang. Tempatkan logam dalam wadah atau tas sampah terpisah.</li>
                <li>Daur Ulang Melalui Pusat Daur Ulang: Bawa logam ke pusat daur ulang logam setempat atau manfaatkan program daur ulang yang mungkin tersedia di komunitas Anda. Proses daur ulang logam membantu mengurangi penggunaan bahan baku alam dan energi.</li>
                <li>Jual Logam Bekas: Jika memungkinkan, Anda dapat menjual logam bekas ke pedagang logam. Beberapa jenis logam, seperti aluminium, memiliki nilai ekonomis yang dapat diuangkan.</li>
                <li>Hindari Membuang di Tempat Pembuangan Sampah Umum: Logam yang dibuang di tempat pembuangan sampah umum bisa menjadi pemborosan sumber daya dan berpotensi merusak lingkungan. Manfaatkan fasilitas daur ulang yang ada untuk memastikan logam didaur ulang dengan benar.</li>
              
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