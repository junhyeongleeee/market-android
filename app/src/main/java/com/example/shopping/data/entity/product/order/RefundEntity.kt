package com.example.shopping.data.entity.product.order

import com.example.shopping.model.product.order.OrderResponse
import com.example.shopping.model.type.OrderType

data class RefundEntity(
    val order: OrderEntity,
    val customer_id: String,
    val status: String,
    val reason: String?,
    val created_at: String,
    val updated_at: String,
)
