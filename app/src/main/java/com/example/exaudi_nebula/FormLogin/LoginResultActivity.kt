package com.example.exaudi_nebula.FormLogin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exaudi_nebula.R
import com.example.exaudi_nebula.databinding.ActivityLoginResultBinding

class LoginResultActivity : AppCompatActivity() {

    // Inisialisasi View Binding
    private lateinit var binding: ActivityLoginResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Setup View Binding
        binding = ActivityLoginResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Perhatikan ID-nya harus 'main_result' sesuai XML kamu
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Ambil data nama yang dikirim dari LoginMainActivity
        val namaUser = intent.getStringExtra("NAMA_USER")

        // 2. Tampilkan ke TextView 'tvWelcome' yang ada di XML kamu
        if (namaUser != null) {
            binding.tvWelcome.text = "Selamat Datang,\n$namaUser!"
        } else {
            binding.tvWelcome.text = "Selamat Datang!"
        }
    }
}