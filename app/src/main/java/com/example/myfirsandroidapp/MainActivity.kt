package com.example.myfirsandroidapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myfirsandroidapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val users = mutableListOf<User>()
    private var deletedUsers = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = User(
            binding.etFirstName.text.toString(),
            binding.etLastName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etAge.text.toString().toIntOrNull() ?: 0
        )

        binding.btnAddUser.setOnClickListener {
            addUser()
        }
        binding.btnRemoveUser.setOnClickListener {
            removeUser()
        }
        binding.btnUpdateUser.setOnClickListener {
            updateUser(user)
        }
    }

    private fun updateUser(user: User) {

        if(ifUserExists(user)) {
            user.firstName = binding.etFirstName.text.toString()
            user.lastName = binding.etLastName.text.toString()
            user.email = binding.etEmail.text.toString()
            user.age = binding.etAge.text.toString().toIntOrNull() ?: 0
        }
    }

    private fun removeUser() {
        val user = User(
            binding.etFirstName.text.toString(),
            binding.etLastName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etAge.text.toString().toIntOrNull() ?: 0
        )
        if(ifUserExists(user)) {
            users.remove(user)
            deletedUsers++
            binding.tvDeletedUsersCount.text = deletedUsers.toString()
            Toast.makeText(this, "User deleted successfully.", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addUser() {
        val user = User(
            binding.etFirstName.text.toString(),
            binding.etLastName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etAge.text.toString().toIntOrNull() ?: 0
        )
        if (isEveryFieldFull(user)) {
            if (isEverythingValid(user)) {
                if (ifUserExists(user)) {
                    Toast.makeText(this, "User already exists.", Toast.LENGTH_SHORT).show()
                } else {
                    binding.tvOperationStatus.text = "Success"
                    binding.tvOperationStatus.setTextColor(
                        ContextCompat.getColor(this, R.color.green)
                    )
                    users.add(user)
                    binding.tvActiveUsersCount.text = users.size.toString()
                    Toast.makeText(this, "User added successfully.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            binding.tvOperationStatus.text = "Error"
            binding.tvOperationStatus.setTextColor(
                ContextCompat.getColor(this, R.color.red))
        }
    }
    private fun isEveryFieldFull(user: User): Boolean {
        if(user.firstName.isEmpty() || user.lastName.isEmpty() || user.email.isEmpty() || user.age == 0) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun isEverythingValid(user: User): Boolean {
        if (isFirstNameValid(this, user.firstName) && isLastNameValid(this, user.lastName)
            && isAgeValid(this, user.age) && isEmailValid(this, user.email)) {
            return true
        }
        return false
    }
    private fun ifUserExists(currentUser: User): Boolean {
        for (user in users) {
            if (user == currentUser) {
                return true
            }
        }
        return false
    }
    private fun isFirstNameValid(context: Context, firstName: String): Boolean {
        val userNameRegex = "^[A-Z][a-z]*$"
        if (firstName.matches(userNameRegex.toRegex())) {
            return true
        } else if (!firstName.first().isUpperCase()){
            Toast.makeText(context, "Invalid name format. Name must start with uppercase letter.", Toast.LENGTH_SHORT).show()
            return false
        } else {
            Toast.makeText(context, "Invalid name format. Please enter a valid name.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun isLastNameValid(context: Context, lastName: String): Boolean {
        val userLastNameRegex = "^[A-Z][a-z]*$"
        if (lastName.matches(userLastNameRegex.toRegex())) {
            return true
        } else if (!lastName.first().isUpperCase()){
            Toast.makeText(context, "Invalid last name format. Last name must start with uppercase letter.", Toast.LENGTH_SHORT).show()
            return false
        } else {
            Toast.makeText(context, "Invalid last name format. Please enter a valid last name.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun isAgeValid(context: Context, age: Int): Boolean {
        if (age <= 0 || age > 120) {
            Toast.makeText(context, "Invalid age range. Please enter a valid age.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun isEmailValid(context: Context, email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        if (email.matches(emailRegex.toRegex()))
            return true
        else
            Toast.makeText(context, "Invalid email format. Please enter a valid email.", Toast.LENGTH_SHORT).show()
        return false
    }
}



