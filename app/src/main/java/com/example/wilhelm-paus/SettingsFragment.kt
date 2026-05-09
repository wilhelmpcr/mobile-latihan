package com.example.wilhelm_paus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wilhelm_paus.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // PERBAIKAN: Inisialisasi binding dengan benar
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data untuk ListView sederhana (ArrayAdapter)
        val menuSettings = arrayOf(
            "Akun Saya",
            "Notifikasi",
            "Privasi & Keamanan",
            "Bantuan",
            "Tentang Aplikasi Wilhelm"
        )

        val adapter = ArrayAdapter(requireContext(), 
            android.R.layout.simple_list_item_1, menuSettings)
        binding.listViewSettings.adapter = adapter

        binding.listViewSettings.setOnItemClickListener { _, _, position, _ ->
            val itemValue = menuSettings[position]
            Toast.makeText(context, "Membuka: $itemValue", Toast.LENGTH_SHORT).show()
        }

        binding.btnSendFeedback.setOnClickListener {
            val feedback = binding.etFeedback.text.toString()
            if (feedback.isNotEmpty()) {
                Toast.makeText(context, "Saran berhasil dikirim! Terima kasih.", Toast.LENGTH_SHORT).show()
                binding.etFeedback.text?.clear()
            } else {
                binding.etFeedback.error = "Harap tulis pesan dulu"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
