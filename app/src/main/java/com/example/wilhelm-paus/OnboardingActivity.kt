package com.example.wilhelm_paus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.wilhelm_paus.FormLogin.LoginMainActivity
import com.example.wilhelm_paus.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            OnboardingItem(
                R.drawable.icon1,
                "Selamat Datang",
                "Aplikasi Bina Desa membantu masyarakat mengakses informasi bantuan sosial secara transparan."
            ),
            OnboardingItem(
                R.drawable.ceklis,
                "Mudah & Cepat",
                "Nikmati berbagai fitur unggulan seperti Kalkulator bantuan dan layanan web dalam satu genggaman."
            ),
            OnboardingItem(
                R.drawable.gambar,
                "Mulai Sekarang",
                "Daftarkan diri Anda dan jadilah bagian dari masyarakat desa yang cerdas teknologi."
            )
        )

        val adapter = OnboardingAdapter(items)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == items.size - 1) {
                    binding.btnNext.text = "Ayo Mulai"
                    binding.tvSkip.visibility = View.GONE
                } else {
                    binding.btnNext.text = "Lanjut"
                    binding.tvSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.tvSkip.setOnClickListener {
            completeOnboarding()
        }

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < items.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                completeOnboarding()
            }
        }
    }

    private fun completeOnboarding() {
        val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("finishedOnboarding", true).apply()

        startActivity(Intent(this, LoginMainActivity::class.java))
        finish()
    }
}
