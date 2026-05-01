package com.example.wilhelm_paus

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.wilhelm_paus.R

class KalkulatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        val toolbar = findViewById<Toolbar>(R.id.toolbarKalkulator)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kalkulator Wilhelm Paus"

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnKubus = findViewById<Button>(R.id.btnHitungKubus)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = etTinggi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = 0.5 * alas * tinggi
            tvHasilSegitiga.text = "Hasil: $hasil"
            Log.d("WILHELM_LOG", "Segitiga - Alas: $alas, Tinggi: $tinggi, Hasil: $hasil")
        }

        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            tvHasilKubus.text = "Hasil: $hasil"
            Log.i("WILHELM_LOG", "Kubus - Sisi: $sisi, Hasil: $hasil")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}