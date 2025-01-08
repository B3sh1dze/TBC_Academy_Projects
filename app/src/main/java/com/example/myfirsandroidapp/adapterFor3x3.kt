package com.example.myfirsandroidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsandroidapp.databinding.Board3x3LayoutBinding

class AdapterFor3x3 : RecyclerView.Adapter<AdapterFor3x3.ViewHolder>(){
    private lateinit var binding : Board3x3LayoutBinding
    class ViewHolder(binding : Board3x3LayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}