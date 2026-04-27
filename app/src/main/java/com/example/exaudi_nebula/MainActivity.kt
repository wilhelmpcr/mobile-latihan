package com.example.exaudi_nebula

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.exaudi_nebula.FormLogin.LoginMainActivity
import com.example.exaudi_nebula.FormLogin.LoginResultActivity
import com.example.exaudi_nebula.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    // Menggunakan ViewBinding sesuai gaya lab kampus
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inisialisasi ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        // Sapaan User di Header
        val namaUser = sharedPreferences.getString("NAMA_USER", "Exaudi")
        binding.tvWelcome.text = "Halo, $namaUser!"

        // Logika Pindah Halaman (Sesuai gaya lab kampus: Router/Hub)
        
        // 1. Ke Kalkulator Bangun Ruang
        binding.cardRumus.setOnClickListener {
            val intent = Intent(this, KalkulatorActivity::class.java)
            startActivity(intent)
        }

        // 2. Ke Laporan/Result (Contoh Menu di Lab)
        binding.cardCustom1.setOnClickListener {
            val intent = Intent(this, LoginResultActivity::class.java)
            startActivity(intent)
        }

        // 3. Ke Web View Bina Desa
        binding.cardWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // 4. Logika Logout dengan MaterialAlertDialogBuilder (Sesuai Lab Kampus)
        binding.cardLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Menggunakan KTX edit untuk menghapus session
                    sharedPreferences.edit {
                        clear()
                        apply()
                    }
                    val intent = Intent(this, LoginMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}
