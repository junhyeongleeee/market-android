package com.example.shopping.data.entity.product.order

import com.example.shopping.model.recyclerView.product.order.RefundModel

data class RefundEntity(
    val order: OrderEntity,
    val customer_id: String,
    val status: String,
    val reason: String?,
    val created_at: String,
    val updated_at: String,
){
    fun toModel() = RefundModel(
        id = order.hashCode().toLong(),
        order = order.toModel(),
        customer_id = customer_id,
        status = status,
        reason = reason,
        created_at = created_at,
        updated_at = updated_at)
}
