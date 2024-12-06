package com.example.myfirsandroidapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val emailInput: EditText = findViewById(R.id.UserEmailinput)
        val usernameInput: EditText = findViewById(R.id.UsernameInput)
        val nameInput: EditText = findViewById(R.id.FirstNameInput)
        val lastNameInput: EditText = findViewById(R.id.LastNameInput)
        val ageInput: EditText = findViewById(R.id.AgeInput)
        val saveButton = findViewById<AppCompatButton>(R.id.SaveButton)
        val clearButton: AppCompatButton = findViewById(R.id.ClearButton)

        removeEverythingIfClearWasClickedTooLong(clearButton)

        saveButton.setOnClickListener {
            val emailInputField = emailInput.text.toString()
            val usernameInputField = usernameInput.text.toString()
            val nameInputField = nameInput.text.toString()
            val lastNameInputField = lastNameInput.text.toString()
            val ageInputField = ageInput.text.toString()


            isEveryFieldFullAndValid(emailInputField, usernameInputField, nameInputField, lastNameInputField, ageInputField, this)
            val areFieldsValid = isEveryFieldFullAndValid(emailInputField, usernameInputField, nameInputField, lastNameInputField, ageInputField, this)


            if (areFieldsValid) {
                val currentUser = saveUserData(emailInputField, usernameInputField, nameInputField, lastNameInputField, ageInputField)
                setContentView(R.layout.after_save_btn_layout)
                displayInfoOnSecondLayout(currentUser)
                val againButton: AppCompatButton = findViewById(R.id.AgainButton)
                againButton.setOnClickListener {
                    setContentView(R.layout.activity_main)
                }
            }
        }
    }

    fun displayInfoOnSecondLayout(currentUser: User) {
        val emailTextView: TextView = findViewById(R.id.EmailTextView)
        emailTextView.text = "Email: " + currentUser.email
        val usernameTextView: TextView = findViewById(R.id.UsernameTextView)
        usernameTextView.text = "Username: " + currentUser.username
        val userFullNameTextView: TextView = findViewById(R.id.UserFullNameTextView)
        userFullNameTextView.text =
            "Full name: " + currentUser.firstName + " " + currentUser.lastName
        val userAgeTextView: TextView = findViewById(R.id.UserAgeTextView)
        userAgeTextView.text = "Age: " + currentUser.age.toString()
    }

    fun saveUserData(emailInput: String, usernameInput: String,
        nameInput: String, lastNameInput: String, ageInput: String
    ): User {
        var newUser = User(emailInput, usernameInput, nameInput, lastNameInput, ageInput.toInt())
        return newUser
    }

    private fun removeEverythingIfClearWasClickedTooLong(clearButton: AppCompatButton) {

        clearButton.setOnLongClickListener {
            val emailInput: EditText = findViewById(R.id.UserEmailinput)
            val usernameInput: EditText = findViewById(R.id.UsernameInput)
            val nameInput: EditText = findViewById(R.id.FirstNameInput)
            val lastNameInput: EditText = findViewById(R.id.LastNameInput)
            val ageInput: EditText = findViewById(R.id.AgeInput)

            emailInput.text.clear()
            usernameInput.text.clear()
            nameInput.text.clear()
            lastNameInput.text.clear()
            ageInput.text.clear()
            true
        }
    }

}
fun isEveryFieldTogetherIsValid(emailInput: String, usernameInput: String, nameInput: String,
                                   lastNameInput: String, ageInput: String, context: Context): Boolean {
    if (isEveryFieldFull(emailInput, usernameInput, nameInput, lastNameInput, ageInput, context)) {
        if(!isEmailValid(emailInput)) {
            Toast.makeText(context, "Invalid email format. Please enter a valid email.", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!isUsernameValid(context, usernameInput)) {
            Toast.makeText(context, "Invalid username format. Please enter a valid username.", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!isUsersRealNameValid(nameInput, context)) {
            Toast.makeText(context, "Invalid name format. Please enter a valid name.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isUsersLastNameValid(lastNameInput, context)) {
            Toast.makeText(context, "Invalid name format. Please enter a valid Last name.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isAgeValid(context, ageInput)) {
            Toast.makeText(context, "Invalid age. Please enter a valid age.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    return true
}
fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    if (email.matches(emailRegex.toRegex()))
        return true
    else
        return false
}
fun isUsernameValid(context: Context, username: String): Boolean {
    val usernameRegex = "^[A-Za-z0-9_]{10,16}\$"
    if (username.length < 10) {
        Toast.makeText(context, "Username must be at least 10 characters long.", Toast.LENGTH_SHORT).show()
        return false
    } else if (username.length > 16) {
        Toast.makeText(context, "Username must be at most 16 characters long.", Toast.LENGTH_SHORT).show()
        return false
    } else {
        if(username.matches(usernameRegex.toRegex())) {
            return true
        }
        else {
            Toast.makeText(context, "Invalid username format. Please enter a valid username.", Toast.LENGTH_SHORT).show()
            return false
        }

    }

}
fun isUsersRealNameValid(usersName: String, context: Context): Boolean {
    val userNameRegex = "^[A-Z][a-z]*$"
    if (usersName.matches(userNameRegex.toRegex())) {
        return true
    } else if (!usersName.first().isUpperCase()){
        Toast.makeText(context, "Invalid name format. Name must start with uppercase letter.", Toast.LENGTH_SHORT).show()
        return false
    } else {
        Toast.makeText(context, "Invalid name format. Please enter a valid name.", Toast.LENGTH_SHORT).show()
        return false
    }
}
fun isUsersLastNameValid(usersLastName: String, context: Context): Boolean {
    var usersLastNameRegex = "[A-Z][a-z]*$"
    if (usersLastName.matches(usersLastNameRegex.toRegex())) {
        return true
    }
    else if (!usersLastName.first().isUpperCase()){
        Toast.makeText(context, "Invalid lastname format. Lastname must start with uppercase letter.", Toast.LENGTH_SHORT).show()
        return false
    } else {
        Toast.makeText(context, "Invalid lastname format. Please enter a valid Lastname.", Toast.LENGTH_SHORT).show()
        return false
    }
}
fun isAgeValid(context: Context, userAge: String): Boolean {
    val convertedUserAge = userAge.toIntOrNull()

    if (convertedUserAge == null) {
        Toast.makeText(context, "Invalid age. Please enter a valid age.", Toast.LENGTH_SHORT).show()
        return false
    } else if (convertedUserAge <= 0 || convertedUserAge > 120) {
        Toast.makeText(context, "Invalid age range. Please enter a valid age.", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}
fun isEveryFieldFull(emailInput: String, usernameInput: String, nameInput: String, lastNameInput: String, ageInput: String, context: Context): Boolean {
    if (emailInput.isEmpty() || usernameInput.isEmpty() || nameInput.isEmpty() || lastNameInput.isEmpty() || ageInput.isEmpty()) {
        Toast.makeText(context, "Every field must be full", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}
fun isEveryFieldFullAndValid(emailInput: String, usernameInput: String, nameInput: String, lastNameInput: String, ageInput: String, context: Context): Boolean {
    if (isEveryFieldFull(emailInput, usernameInput, nameInput, lastNameInput, ageInput, context)
        && isEveryFieldTogetherIsValid(emailInput, usernameInput, nameInput, lastNameInput, ageInput, context)) {
        return true
    } else {
        return false
    }
}

