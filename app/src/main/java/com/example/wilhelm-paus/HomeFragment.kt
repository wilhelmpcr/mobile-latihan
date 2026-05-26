package com.example.wilhelm_paus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wilhelm_paus.FormLogin.LoginMainActivity
import com.example.wilhelm_paus.FormLogin.LoginResultActivity
import com.example.wilhelm_paus.api.NewsAdapter
import com.example.wilhelm_paus.api.NewsResponse
import com.example.wilhelm_paus.api.RetrofitClient
import com.example.wilhelm_paus.data.api.CatFactApiClient
import com.example.wilhelm_paus.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var newsAdapter: NewsAdapter

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

        setupRecyclerView()
        setupGallery()
        fetchNews()
        loadCatFact()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

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

        // Menu 4: Logout
        binding.cardLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Konfirmasi Keluar")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi Bina Desa?")
                .setPositiveButton("Ya, Keluar") { _, _ ->
                    sharedPreferences.edit().clear().apply()
                    val intent = Intent(requireActivity(), LoginMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    private fun setupGallery() {
        val galleryItems = listOf(
            OnboardingItem(
                R.drawable.background,
                "Kegiatan Desa",
                "Gotong royong membangun infrastruktur desa yang lebih baik."
            ),
            OnboardingItem(
                R.drawable.gambar,
                "Penyuluhan",
                "Edukasi masyarakat mengenai pentingnya teknologi di era digital."
            ),
            OnboardingItem(
                R.drawable.eemgtg,
                "Bantuan Sosial",
                "Distribusi bantuan tepat sasaran untuk warga yang membutuhkan."
            ),
            OnboardingItem(
                R.drawable.logobansos,
                "Sistem Terpadu",
                "Akses informasi bantuan sosial dalam satu aplikasi."
            )
        )

        val galleryAdapter = GalleryAdapter(galleryItems)
        binding.rvGallery.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = galleryAdapter
        }
    }

    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(listOf())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun fetchNews() {
        RetrofitClient.instance.getNationalNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.data?.posts ?: listOf()
                    
                    if (newsList.isNotEmpty()) {
                        val headline = newsList[0]
                        binding.cardHeadline.visibility = View.VISIBLE
                        binding.tvHeadlineTitle.text = headline.title
                        Glide.with(this@HomeFragment)
                            .load(headline.thumbnail)
                            .into(binding.imgHeadline)
                        
                        newsAdapter.updateData(newsList.drop(1))
                    } else {
                        newsAdapter.updateData(listOf())
                    }
                } else {
                    Toast.makeText(context, "Gagal memuat berita", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
