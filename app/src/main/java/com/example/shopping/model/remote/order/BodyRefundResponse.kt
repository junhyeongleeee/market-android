package com.example.shopping.model.remote.order

import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.response.order.RefundResponse

data class BodyRefundResponse(
    val total: Int,
    val refunds: List<RefundResponse>
)
