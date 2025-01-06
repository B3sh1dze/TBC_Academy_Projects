package com.example.myfirsandroidapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a13davaleba_new.MessagesFragment
import com.example.myfirsandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val messagesList: MutableList<Message> = mutableListOf(
        Message(id = 1, text = "Hello", time = "12:00", messageType = MessageType.SENT),
        Message(id = 2, text = "Hi", time = "12:01", messageType = MessageType.RECEIVED)
    )
    private val adapter: MessageAdapter by lazy { MessageAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment()
        setupSendButton()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frvMessages, MessagesFragment())
            .commit()
    }

    private fun setupSendButton() {
        binding.btnSendMessage.setOnClickListener {
            val inputText = binding.tvMessage.text.toString().trim()
            if (inputText.isNotEmpty()) {
                addMessage(inputText)
                binding.tvMessage.text!!.clear() // Clear the input field
            }
        }
    }

    private fun addMessage(text: String) {
        val newMessage = Message(
            id = messagesList.size + 1,
            text = text,
            time = "12:30", // Replace with actual time logic
            messageType = if (messagesList.size % 2 == 0) MessageType.SENT else MessageType.RECEIVED
        )
        messagesList.add(0, newMessage) // Add the new message at the top
        adapter.submitList(messagesList.toList()) // Notify adapter
    }
}



