package com.example.myfirsandroidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfirsandroidapp.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BoardAdapter
    private var boardSize: Int = 3
    private var currentPlayer = "X"
    private var isGameOver = false
    private val board: Array<Array<String>> by lazy { Array(boardSize) { Array(boardSize) { "" } } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            boardSize = it.getInt("boardSize", 3)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.goBackBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, MenuFragment())
                .commit()
        }

        setupBoard()
    }

    private fun setupBoard() {
        adapter = BoardAdapter(boardSize) { position ->
            if (!isGameOver) {
                handleCellClick(position)
            }
        }
        // Display RecyclerView with GridLayout
        binding.gridLayout.layoutManager = GridLayoutManager(requireContext(), boardSize)
        binding.gridLayout.adapter = adapter
    }

    private fun handleCellClick(position: Int) {
        val row = position / boardSize
        val col = position % boardSize

        val button = binding.gridLayout.findViewHolderForAdapterPosition(position)?.itemView as? Button
        if (button != null && button.text.isEmpty()) {
            button.text = currentPlayer
            board[row][col] = currentPlayer
            if (checkWin(row, col)) {
                Toast.makeText(requireContext(), "$currentPlayer wins!", Toast.LENGTH_SHORT).show()
                isGameOver = true
            }
            else if (isBoardFull()) {
                Toast.makeText(requireContext(), "It's a draw!", Toast.LENGTH_SHORT).show()
            }
            if (!isGameOver) {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    private fun checkWin(row: Int, col: Int): Boolean {
        //Check rows
        if ((0 until boardSize).all { board[row][it] == currentPlayer }) return true

        // Check columns
        if ((0 until boardSize).all { board[it][col] == currentPlayer }) return true

        // Check main diagonal
        if (row == col && (0 until boardSize).all { board[it][it] == currentPlayer }) return true

        // Check anti-diagonal
        if (row + col == boardSize - 1 && (0 until boardSize).all { board[it][boardSize - it - 1] == currentPlayer }) return true

        return false
    }

    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it.isNotEmpty() } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
