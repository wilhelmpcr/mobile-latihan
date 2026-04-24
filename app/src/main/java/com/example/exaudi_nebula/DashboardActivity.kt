package com.example.exaudi_nebula

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.exaudi_nebula.FormLogin.LoginMainActivity
import com.example.exaudi_nebula.FormLogin.LoginResultActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.toolbarDashboard)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dashboard Bina Desa"

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        val cardRumus = findViewById<MaterialCardView>(R.id.cardRumus)
        val cardCustom1 = findViewById<MaterialCardView>(R.id.cardCustom1)
        val cardCustom2 = findViewById<MaterialCardView>(R.id.cardCustom2)
        val cardLogout = findViewById<MaterialCardView>(R.id.cardLogout)
        val cardWebView = findViewById<MaterialCardView>(R.id.cardWebView)

        cardRumus.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        cardCustom1.setOnClickListener {
            startActivity(Intent(this, LoginMainActivity::class.java))
        }

        cardCustom2.setOnClickListener {
            startActivity(Intent(this, LoginResultActivity::class.java))
        }

        cardWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        cardLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    sharedPreferences.edit().clear().apply()
                    val intent = Intent(this, LoginMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(findViewById(android.R.id.content), "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}