package com.example.shopping.model.product.order

import com.example.shopping.model.type.OrderType

data class OrderRefundCancelModel(
    val reason: String?,
    val status: String
)
