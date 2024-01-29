package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityAnorganikArticleBinding

class AnorganikArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnorganikArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnorganikArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Plastik : Plastik adalah salah satu contoh paling mencolok dari sampah anorganik. Botol air, kemasan makanan, dan tas plastik adalah produk-produk sehari-hari yang cenderung bertahan selama ratusan tahun di alam. </li>
                <li>Kaca : Botol kaca, wadah makanan kaca, dan pecahan kaca merupakan jenis sampah anorganik lainnya. Kaca membutuhkan waktu sangat lama untuk terurai, dan seringkali berbahaya jika tidak dikelola dengan baik karena dapat menyebabkan luka atau pencemaran lingkungan.</li>
                <li>Logam : Kaleng, tutup botol, dan barang-barang logam lainnya adalah contoh sampah anorganik yang dapat bertahan lama di lingkungan.</li>
                <li>Baterai : Baterai merupakan salah satu bentuk sampah anorganik yang dapat menyebabkan dampak serius pada lingkungan jika tidak dikelola dengan benar. Baterai mengandung bahan kimia beracun seperti timbal, merkuri, dan kadmiun. Karena sifatnya yang berbahaya, pengelolaan limbah baterai harus dilakukan dengan hati-hati untuk mencegah pencemaran tanah dan air.</li>
                <li>Pakaian : Meskipun pakaian umumnya terbuat dari bahan organik seperti kapas atau serat alami, beberapa jenis pakaian modern mengandung bahan sintetis yang sulit terurai. Serat sintetis seperti poliester atau nilon dapat memakan waktu puluhan hingga ratusan tahun untuk terurai. </li>
                <li>Sepatu : Sepatu seringkali terbuat dari campuran berbagai bahan anorganik seperti karet, plastik, logam, dan bahan sintetis. Proses manufaktur dan jenis bahan yang digunakan dapat menyulitkan proses daur ulang.</li>
                
              
            </ol>
        """.trimIndent()

        binding.isiContoh.text = fromHtml(orderedListText)
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