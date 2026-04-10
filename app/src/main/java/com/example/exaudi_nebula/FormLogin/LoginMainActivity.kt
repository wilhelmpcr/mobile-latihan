package com.example.exaudi_nebula.FormLogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.DashboardActivity  // ← TAMBAH IMPORT
import com.example.exaudi_nebula.databinding.ActivityLoginMainBinding

class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logika Klik Tombol Login
        binding.btnLogin.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val pass = binding.inputPassword.text.toString()

            // Validasi Simpel: Jangan biarkan kosong
            if (nama.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Nama dan Password harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Halo $nama, Selamat Datang!", Toast.LENGTH_SHORT).show()

                // Pindah ke DashboardActivity
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish() // ← TAMBAH INI
            }
        }
    }
}