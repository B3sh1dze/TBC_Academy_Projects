package com.example.myfirsandroidapp

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(private val boardSize: Int, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {


    class BoardViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val button = Button(parent.context)
        val screenWidth = parent.context.resources.displayMetrics.widthPixels

        val buttonSize = screenWidth / boardSize

        button.layoutParams = ViewGroup.LayoutParams(buttonSize, buttonSize)
        return BoardViewHolder(button)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.button.text = ""
        holder.button.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = boardSize * boardSize
}
