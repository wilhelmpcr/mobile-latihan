package com.example.wilhelm_paus.FormLogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wilhelm_paus.databinding.ActivityLoginResultBinding

class LoginResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityLoginResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dengan tombol back
        setSupportActionBar(binding.toolbarLoginResult)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Hasil Autentikasi"

        // Ambil data nama dari intent
        val namaUser = intent.getStringExtra("NAMA_USER")
        if (namaUser != null) {
            binding.tvUserDetail.text = "Selamat bergabung, $namaUser!\nAkun Anda telah aktif di Sistem Bina Desa."
        }

        // Tombol Kembali manual di dalam kartu
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
