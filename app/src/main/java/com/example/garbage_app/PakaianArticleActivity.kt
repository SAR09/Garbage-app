package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityPakaianArticleBinding

class PakaianArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPakaianArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pakaian_article)

        binding = ActivityPakaianArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemilahan Sampah: Pisahkan pakaian yang sudah tidak dapat digunakan lagi dari sampah lainnya. Beberapa jenis pakaian mungkin bisa didaur ulang atau diolah kembali.</li>
                <li>Daur Ulang Pakaian: Beberapa pakaian dapat didaur ulang menjadi barang-barang lain, seperti lap atau kain untuk proyek kerajinan. Pastikan untuk memisahkan pakaian yang bisa didaur ulang dan membawanya ke pusat daur ulang tekstil jika ada di wilayah Anda.</li>
                <li>Donasi atau Jual Pakaian Bekas: Jika pakaian masih dalam kondisi baik dan dapat digunakan, pertimbangkan untuk mendonasikan atau menjualnya. Banyak lembaga amal menerima sumbangan pakaian bekas, dan platform online menyediakan tempat untuk menjual pakaian bekas.</li>
                <li>Proyek Kreatif: Manfaatkan kreativitas Anda untuk membuat proyek-proyek seni atau kerajinan dari potongan-potongan pakaian bekas. Anda dapat membuat bantal, tas, atau hiasan lainnya.</li>
              
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