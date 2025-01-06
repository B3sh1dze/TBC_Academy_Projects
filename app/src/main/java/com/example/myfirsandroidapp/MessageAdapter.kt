package com.example.myfirsandroidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myfirsandroidapp.databinding.ReceivedMessageLayoutBinding
import com.example.myfirsandroidapp.databinding.SendMessageLayoutBinding

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffUtil()) {

    private var counter = 0

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == SENT_MESSAGE) {
            SendMessageViewHolder(SendMessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            ReceivedMessageViewHolder(ReceivedMessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (counter % 2 != 0) {
            (holder as SendMessageViewHolder)
                holder.onBind()
        } else {
            (holder as ReceivedMessageViewHolder)
            holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).messageType == MessageType.SENT) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }
    }

    inner class ReceivedMessageViewHolder(private val binding: ReceivedMessageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val message = getItem(adapterPosition)
            binding.tvMessageText.text = message.text
            binding.tvMessageTime.text = message.time
            counter++
        }
    }

    inner class SendMessageViewHolder(private val binding: SendMessageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val message = getItem(adapterPosition)
            binding.tvMesageText.text = message.text
            binding.tvMessageTime.text = message.time
            counter++
        }

    }


    class MessageDiffUtil : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }
}