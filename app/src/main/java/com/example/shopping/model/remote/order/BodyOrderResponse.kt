package com.example.shopping.model.remote.order

import com.example.shopping.data.entity.product.order.OrderEntity

data class BodyOrderResponse(
    val total: Int,
    val orders: List<OrderEntity>
)
