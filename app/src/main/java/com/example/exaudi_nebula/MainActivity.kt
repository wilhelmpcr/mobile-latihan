package com.example.exaudi_nebula

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View Segitiga
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        // Inisialisasi View Kubus
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnKubus = findViewById<Button>(R.id.btnHitungKubus)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        // Aksi Hitung Segitiga
        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = etTinggi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = 0.5 * alas * tinggi
            tvHasilSegitiga.text = "Hasil: $hasil"

            // Menggunakan Logcat untuk debugging
            Log.d("EXAUDI_LOG", "Hitung Segitiga - Alas: $alas, Tinggi: $tinggi, Hasil: $hasil")
        }

        // Aksi Hitung Kubus
        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            tvHasilKubus.text = "Hasil: $hasil"

            // Menggunakan Logcat untuk debugging
            Log.i("EXAUDI_LOG", "Hitung Kubus - Sisi: $sisi, Hasil: $hasil")
        }
    }
}