package com.example.myappforworking

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myappforworking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val anagrams = mutableListOf<String>()
    private val lengthToStringsMap = mutableMapOf<Int, MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val saveBtn = binding.anagramSaveBtn
        val outputBtn = binding.anagramOutputBtn
        val clearBtn = binding.clearBtn

        saveBtn.setOnClickListener {
            val anagram = binding.anagramEnterBtn.text.toString()
            if (anagram.isEmpty()) {
                Toast.makeText(this, "Anagram field is empty", Toast.LENGTH_SHORT).show()
            }
            anagrams.add(anagram)
            for (anagram in anagrams) {
                val anagramLength = anagram.length
                if(lengthToStringsMap.containsKey(anagramLength)) {
                    lengthToStringsMap[anagramLength]?.add(anagram)
                } else {
                    lengthToStringsMap[anagramLength] = mutableListOf(anagram)
                }
            }
            binding.anagramEnterBtn.text?.clear()
        }
        outputBtn.setOnClickListener {
            binding.tvShowAnagrams.text = lengthToStringsMap.toString()
        }

        clearBtn.setOnClickListener {
            anagrams.clear()
            lengthToStringsMap.clear()
            binding.tvShowAnagrams.text = ""
        }
    }
}