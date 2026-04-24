package com.example.exaudi_nebula

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.FormLogin.LoginMainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sharedPreferences.getBoolean("isLogin", false)
            val intent = if (isLogin) {
                Intent(this, DashboardActivity::class.java)
            } else {
                Intent(this, LoginMainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000) // 2 detik, bisa Anda atur
    }
}