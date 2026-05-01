package com.example.wilhelm_paus.FormLogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wilhelm_paus.MainActivity
import com.example.wilhelm_paus.databinding.ActivityLoginMainBinding

class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val nama = binding.inputNama.text.toString().trim()
            val pass = binding.inputPassword.text.toString().trim()

            if (nama.isEmpty() || pass.isEmpty()) {
                if (nama.isEmpty()) binding.inputNama.error = "Username wajib diisi"
                if (pass.isEmpty()) binding.inputPassword.error = "Password wajib diisi"
                Toast.makeText(this, "Silahkan lengkapi kredensial Anda!", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan sesi login
                sharedPreferences.edit().apply {
                    putBoolean("isLogin", true)
                    putString("NAMA_USER", nama)
                    apply()
                }

                Toast.makeText(this, "Selamat Datang di Sistem Bina Desa, $nama!", Toast.LENGTH_SHORT).show()

                // Arahkan ke Dashboard Utama (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
