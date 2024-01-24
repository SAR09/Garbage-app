package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityKardusArticleBinding

class KardusArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKardusArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kardus_article)

        binding = ActivityKardusArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Pemisahan Sampah: Pisahkan kardus dari sampah lainnya. Tempatkan kardus dalam wadah atau tas sampah terpisah agar mudah diidentifikasi dan diolah secara terpisah.</li>
                <li>Daur Ulang: Salah satu cara terbaik untuk mengelola sampah kardus adalah dengan mendaur ulangnya. Pastikan untuk membawa kardus ke pusat daur ulang atau menggunakan layanan pengumpulan kardus daur ulang yang mungkin tersedia di komunitas Anda</li>
                <li>Reutilisasi: Jika memungkinkan, pertimbangkan untuk menggunakan kembali kardus. Kardus yang masih dalam kondisi baik dapat digunakan untuk menyimpan barang-barang di rumah atau digunakan kembali untuk keperluan pindahan.</li>
                <li>Penjualan atau Donasi: Jika Anda memiliki kardus yang masih dalam kondisi baik, Anda dapat mempertimbangkan untuk menjualnya atau mendonasikannya ke orang lain yang membutuhkan untuk keperluan pindahan atau penyimpanan..</li>
              
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