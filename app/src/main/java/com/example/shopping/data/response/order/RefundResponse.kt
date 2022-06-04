package com.example.shopping.data.response.order

import com.example.shopping.data.entity.product.order.RefundEntity
import com.example.shopping.model.product.order.OrderResponse
import com.example.shopping.model.type.OrderType

data class RefundResponse(
    val order: OrderResponse,
    val customer_id: String,
    val status: String,
    val reason: String?,
    val created_at: String,
    val updated_at: String,
){
    fun toEntity() = RefundEntity(
        order = order.toEntity(),
        customer_id, status, reason, created_at, updated_at
    )
}
