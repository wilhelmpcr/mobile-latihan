package com.example.wilhelm_paus.message

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wilhelm_paus.databinding.ActivityMessageBinding
import com.google.android.material.tabs.TabLayoutMediator

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbarMessage)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup ViewPager2 with Adapter
        val adapter = FragmentAdapter(this)
        binding.viewPager.adapter = adapter

        // Setup TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pesan Masuk"
                else -> "Pesan Terkirim"
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
