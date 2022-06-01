package com.example.shopping.data.entity.product.order

import com.example.shopping.model.type.OrderType

data class OrderEntity(
    val uid: String,
    val customer_id: String,
    val total_price: Integer,
    val status: OrderType,
    val ordered_at: String,
    val updated_at: String,
    val items: List<OrderItemEntity>
)
