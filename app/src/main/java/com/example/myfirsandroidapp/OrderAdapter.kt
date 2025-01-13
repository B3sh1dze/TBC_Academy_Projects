package com.example.myfirsandroidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsandroidapp.databinding.FragmentOrderDetailsBinding

class OrderAdapter(private val orderList: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = FragmentOrderDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.onBind(order)
    }

    inner class OrderViewHolder(private val binding: FragmentOrderDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(order: Order) {
            binding.ivOrder.setImageDrawable(ContextCompat.getDrawable(binding.root.context, order.image))// Assuming image is a drawable resource
            binding.tvOrderName.text = order.name
            binding.tvOrderQuantity.text = "Quantity: ${order.quantity}"
            binding.tvOrderPrice.text = "Price: $${order.price}"
            binding.tvOrderStatus.text = order.status.name
            binding.tvOrderColor.setBackgroundColor(order.color) // Set background color
        }
    }
}
