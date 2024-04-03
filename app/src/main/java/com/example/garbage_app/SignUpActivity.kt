package com.example.garbage_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.garbage_app.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Atur listener klik untuk tombol kembali (asumsikan namanya backButton)
        binding.backbt.setOnClickListener {
            // Kembali ke SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signupbt.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Pindah ke SignInActivity setelah pendaftaran berhasil
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Menangani kesalahan saat pendaftaran
                            val errorMessage = when (it.exception) {
                                is FirebaseAuthUserCollisionException -> "Email sudah terdaftar"
                                else -> "Gagal mendaftar: ${it.exception?.message}"
                            }
                            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Kolom yang kosong tidak diizinkan !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
