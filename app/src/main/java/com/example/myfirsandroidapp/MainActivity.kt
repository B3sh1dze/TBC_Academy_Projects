package com.example.myappforworking

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myappforworking.databinding.ActivityMainBinding

/*import com.example.myappforworking.databinding.ActivityMainBinding
import com.example.myappforworking.databinding.ActivityMainBinding
import com.example.myfirsandroidapp.databinding.ActivityMainBinding*/


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userMap = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addUserBtn = binding.addUserBtn
        val searchEmailBtn = binding.searchEmailBtn

        addUserBtn.setOnClickListener {
            val fullName = binding.etFullNameInput.text.toString()
            val email = binding.etEmailInput.text.toString()
            if (isFullNameValid(fullName) && isEmailValid(this, email)) {
                userMap[email] = fullName
                binding.tvUsersCount.text = "Users -> " + userMap.size.toString()
                binding.etFullNameInput.text?.clear()
                binding.etEmailInput.text?.clear()
            }
        }
        searchEmailBtn.setOnClickListener {
            val searchEmail = binding.etSearchEmail.text.toString()
            val showUserInfo = binding.showUserInfo
            if (searchEmail.isEmpty()) {
                Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show()
            } else if (userMap.containsKey(searchEmail)) {
                val fullName = userMap[searchEmail]
                showUserInfo.text = "User -> $fullName \n Email -> $searchEmail"
            } else {
                showUserInfo.text = "User not found"
            }
            binding.etSearchEmail.text?.clear()
        }
    }
    private fun isEmailValid(context: Context, email: String): Boolean {
        val emailPattern = "[a-z0-9._%+-]+@[a-z0-9.]+.[a-z]"
        val emailRegex = Regex(emailPattern)
        if (email.isEmpty()) {
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show()
            return false
        } else if (emailRegex.matches(email)) {
            return true
        } else {
            Toast.makeText(this, "Email format is invalid.", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun isFullNameValid(fullName: String): Boolean {
        val fullNamePattern = "^[A-Z][a-z]+( [A-Z][a-z]+)+$"
        val fullNameRegex = Regex(fullNamePattern)
        if (fullName.isEmpty()) {
            Toast.makeText(this, "Name field is empty", Toast.LENGTH_SHORT).show()
            return false
        } else if (fullNameRegex.matches(fullName)) {
            return true
        } else {
            Toast.makeText(this, "Name format is invalid.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}