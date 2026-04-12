package com.example.exaudi_nebula

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.FormLogin.LoginMainActivity
import com.example.exaudi_nebula.FormLogin.LoginResultActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val cardRumus = findViewById<MaterialCardView>(R.id.cardRumus)
        val cardCustom1 = findViewById<MaterialCardView>(R.id.cardCustom1)
        val cardCustom2 = findViewById<MaterialCardView>(R.id.cardCustom2)
        val cardLogout = findViewById<MaterialCardView>(R.id.cardLogout)

        // Tombol 1: ke MainActivity (Rumus Bangun Ruang)
        cardRumus.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("JUDUL", "Rumus Bangun Ruang")
            intent.putExtra("DESKRIPSI", "Hitung volume dan luas permukaan")
            startActivity(intent)
        }

        // Tombol 2: ke LoginMainActivity (Custom 1)
        cardCustom1.setOnClickListener {
            val intent = Intent(this, LoginMainActivity::class.java)
            intent.putExtra("JUDUL", "Custom Menu 1")
            intent.putExtra("DESKRIPSI", "Halaman Login Form")
            startActivity(intent)
        }

        // Tombol 3: ke LoginResultActivity (Custom 2)
        cardCustom2.setOnClickListener {
            val intent = Intent(this, LoginResultActivity::class.java)
            intent.putExtra("JUDUL", "Custom Menu 2")
            intent.putExtra("DESKRIPSI", "Halaman Hasil Login")
            startActivity(intent)
        }

        // Tombol 4: Logout ke LoginMainActivity
        cardLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this, LoginMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(findViewById(android.R.id.content),
                        "Logout dibatalkan",
                        Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}