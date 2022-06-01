package com.example.shopping.model.product.order

import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.type.OrderType

data class OrderResponse(
    val uid: String,
    val customer_id: String,
    val total_price: Integer,
    val status: OrderType,
    val ordered_at: String,
    val updated_at: String,
    val items: List<OrderItemEntity>
){
    fun toEntity() = OrderEntity(
        uid, customer_id, total_price, status, ordered_at, updated_at, items
    )
}
