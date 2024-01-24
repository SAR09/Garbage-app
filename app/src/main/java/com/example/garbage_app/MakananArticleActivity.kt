package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityMakananArticleBinding

class MakananArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakananArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makanan_article)


        binding = ActivityMakananArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Kompos: Salah satu cara terbaik untuk mengelola sampah sisa makanan adalah dengan membuat kompos. Anda dapat memisahkan sisa makanan yang dapat terkompos (seperti sisa sayuran, buah, dan bahan organik lainnya) dari sampah lainnya. Tempatkan bahan-bahan ini dalam wadah kompos dan biarkan mereka terurai secara alami menjadi pupuk yang berguna untuk tanaman.</li>
                <li>Penggiling Sampah Organik: Penggiling sampah organik adalah perangkat yang dapat menghancurkan sisa makanan menjadi partikel-partikel kecil. Partikel-partikel ini dapat kemudian dibuang ke sistem pembuangan air rumah tangga atau digunakan untuk kompos.Penggiling Sampah Organik: Penggiling sampah organik adalah perangkat yang dapat menghancurkan sisa makanan menjadi partikel-partikel kecil. Partikel-partikel ini dapat kemudian dibuang ke sistem pembuangan air rumah tangga atau digunakan untuk kompos.</li>
                <li>Pengelolaan Sampah Secara Terpisah: Pastikan bahwa sampah sisa makanan dipisahkan dari sampah lainnya agar dapat diolah dengan cara yang sesuai.</li>
                
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