package com.example.wilhelm_paus.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wilhelm_paus.data.AppDatabase
import com.example.wilhelm_paus.data.entity.MessageEntity
import com.example.wilhelm_paus.databinding.FragmentMessageBinding
import kotlinx.coroutines.launch

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var db: AppDatabase
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        
        setupRecyclerView()

        binding.btnSend.setOnClickListener {
            val content = binding.etMessage.text.toString().trim()
            if (content.isNotEmpty()) {
                saveMessage(content)
            } else {
                Toast.makeText(requireContext(), "Pesan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        fetchMessages()
    }

    private fun setupRecyclerView() {
        adapter = MessageAdapter(mutableListOf()) { message ->
            deleteMessage(message)
        }
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MessageFragment.adapter
        }
    }

    private fun fetchMessages() {
        lifecycleScope.launch {
            val messages = db.messageDao().getAllMessages()
            adapter.updateData(messages)
            if (messages.isNotEmpty()) {
                binding.rvMessages.scrollToPosition(0)
            }
        }
    }

    private fun saveMessage(content: String) {
        lifecycleScope.launch {
            val newMessage = MessageEntity(
                sender = "Saya", // Dummy sender
                content = content
            )
            db.messageDao().insertMessage(newMessage)
            binding.etMessage.text.clear()
            fetchMessages()
        }
    }

    private fun deleteMessage(message: MessageEntity) {
        lifecycleScope.launch {
            db.messageDao().deleteMessage(message)
            fetchMessages()
            Toast.makeText(requireContext(), "Pesan dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
