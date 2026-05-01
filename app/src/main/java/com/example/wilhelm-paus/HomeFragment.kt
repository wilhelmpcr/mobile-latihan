package com.example.wilhelm_paus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.wilhelm_paus.FormLogin.LoginMainActivity
import com.example.wilhelm_paus.FormLogin.LoginResultActivity
import com.example.wilhelm_paus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        
        val namaUser = sharedPreferences.getString("NAMA_USER", "Wilhelm")
        binding.tvWelcome.text = "Halo, $namaUser!"

        // Menu 1: Kalkulator
        binding.cardRumus.setOnClickListener {
            startActivity(Intent(requireContext(), KalkulatorActivity::class.java))
        }

        // Menu 2: Hasil Login
        binding.cardCustom1.setOnClickListener {
            val intent = Intent(requireContext(), LoginResultActivity::class.java)
            intent.putExtra("NAMA_USER", namaUser)
            startActivity(intent)
        }

        // Menu 3: Web View
        binding.cardWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Menu 4: Logout (Gunakan requireActivity() agar Dialog muncul instan)
        binding.cardLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Konfirmasi Keluar")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi Bina Desa?")
            builder.setPositiveButton("Ya, Keluar") { _, _ ->
                // Hapus Session
                sharedPreferences.edit().clear().apply()
                
                // Pindah ke Login dan bersihkan Backstack
                val intent = Intent(requireActivity(), LoginMainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            builder.setNegativeButton("Batal", null)
            builder.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
