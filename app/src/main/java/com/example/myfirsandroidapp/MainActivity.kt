package com.example.myfirsandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirsandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user1 = User(
            id = 1,
            firstName = "გრიშა",
            lastName = "ონიანი",
            birthday = 1724647601641,
            address = "სტალინის სახლმუზეუმი",
            email = "grisha@mail.ru"
        )

        val user2 = User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = 1714647601641,
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com"
        )

        val searchParameter = binding.etenterParameter.text.toString()
        binding.btnSearch.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddNewUser())
                .addToBackStack(null)
                .commit()
            search(searchParameter)
        }
    }

        private fun search(searchParameter: String): User {
            for (user in users) {
                if(searchParameter == user.firstName || searchParameter == user.lastName
                    || searchParameter == user.address || searchParameter == user.email || searchParameter.toInt() == user.id
                    || searchParameter.toLong() == user.birthday) {
                    return user
                }
            }
            return null!!
        }
}



