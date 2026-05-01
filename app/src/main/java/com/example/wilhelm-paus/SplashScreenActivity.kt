package com.example.wilhelm_paus

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.wilhelm_paus.FormLogin.LoginMainActivity
import com.example.wilhelm_paus.R

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sharedPreferences.getBoolean("isLogin", false)

            // Arahkan ke MainActivity (Halaman Utama dengan Bottom Navigation)
            val intent = if (isLogin) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginMainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}
