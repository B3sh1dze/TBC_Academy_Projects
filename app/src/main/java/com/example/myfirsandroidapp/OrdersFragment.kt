package com.example.myfirsandroidapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class OrdersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter
    private val orderList = listOf(
        Order(
            id = 1,
            name = "Order 1",
            quantity = 3,
            price = 29.99,
            status = OrderStatus.ACTIVE,
            image = R.drawable.default_image,
            color = Color.RED
        ),
        Order(
            id = 2,
            name = "Order 2",
            quantity = 1,
            price = 49.99,
            status = OrderStatus.COMPLETED,
            image = R.drawable.cart_icon,
            color = Color.RED
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = OrderAdapter(orderList)
        recyclerView.adapter = adapter

        return view
    }
}
