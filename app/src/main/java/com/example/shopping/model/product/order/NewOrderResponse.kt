package com.example.shopping.model.product.order

import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.type.OrderType

data class NewOrderResponse(
    val total: Int,
    val orders: List<OrderEntity>
)
