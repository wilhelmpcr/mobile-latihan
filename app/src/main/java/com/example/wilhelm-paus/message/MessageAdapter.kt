package com.example.wilhelm_paus.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wilhelm_paus.data.entity.MessageEntity
import com.example.wilhelm_paus.databinding.ItemMessageBinding
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(
    private val messages: MutableList<MessageEntity>,
    private val onDeleteClick: (MessageEntity) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.binding.tvSender.text = message.sender
        holder.binding.tvContent.text = message.content
        
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        holder.binding.tvTime.text = sdf.format(Date(message.timestamp))

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    fun updateData(newMessages: List<MessageEntity>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }
}
