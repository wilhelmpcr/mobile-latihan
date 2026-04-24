package com.example.exaudi_nebula.FormLogin

import android.content.Intent
import android.content.SharedPreferences  // TAMBAHAN
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.DashboardActivity
import com.example.exaudi_nebula.databinding.ActivityLoginMainBinding

class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMainBinding
    private lateinit var sharedPreferences: SharedPreferences  // TAMBAHAN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TAMBAHAN: inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        // Logika Klik Tombol Login
        binding.btnLogin.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val pass = binding.inputPassword.text.toString()

            if (nama.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Nama dan Password harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                // TAMBAHAN: simpan status login
                sharedPreferences.edit().putBoolean("isLogin", true).apply()

                Toast.makeText(this, "Halo $nama, Selamat Datang!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}