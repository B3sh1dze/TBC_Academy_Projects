package com.example.myfirsandroidapp

import android.graphics.Color
import android.widget.ImageView

data class Order(val id: Int,
                 val name: String,
                 val quantity: Int,
                 val price: Double,
                 val status: OrderStatus,
                 val image : Int,
                 val color: Int)