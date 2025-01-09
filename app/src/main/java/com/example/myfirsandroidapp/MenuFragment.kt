package com.example.myfirsandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfirsandroidapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBoxes = listOf(binding.checkbox3x3, binding.checkbox4x4, binding.checkbox5x5)
        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkBoxes.filter { it != checkBox }.forEach { it.isChecked = false }
                }
            }
        }

        binding.startGameButton.setOnClickListener {
            val boardSize = when {
                binding.checkbox3x3.isChecked -> 3
                binding.checkbox4x4.isChecked -> 4
                binding.checkbox5x5.isChecked -> 5
                else -> {
                    Toast.makeText(context, "Please select a grid size", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val gameFragment = BoardFragment().apply {
                arguments = Bundle().apply {
                    putInt("boardSize", boardSize)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, gameFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}