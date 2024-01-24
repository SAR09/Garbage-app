package com.example.garbage_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.garbage_app.databinding.ActivityBatteryArticleBinding
import com.example.garbage_app.databinding.ActivitySignInBinding

class BatteryArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBatteryArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_article)

        binding = ActivityBatteryArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val orderedListText = """
            <ol>
                <li>Tempatkan baterai bekas pada tempat terpisah dari sampah lain.</li>
                <li>Pasang selotip bening yang tak konduktif pada kedua ujung baterai.</li>
                <li>Masukkan baterai bekas dalam plastik atau wadah khusus yang tidak bersifat konduktif.</li>
                <li>Beri tanda atau nama pada wadah tersebut dengan informasi limbah baterai dan B3.</li>
                <li>Kumpulkan semua limbah B3 pada tempat penampungan khusus sementara yang telah disepakati di wilayah RT atau RW.</li>
                <li>Kirim Limbah B3 ke pengelola sampah B3 yang sudah memenuhi standar dan berizin.</li>
                
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