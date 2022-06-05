package com.example.shopping.presentation.detail.navigation.order

import com.example.shopping.data.entity.product.order.OrderEntity

sealed class OrderState {
    object UnInitialized: OrderState()
    object Loading: OrderState()
    data class Success(
        val orderEntity: OrderEntity
    ): OrderState()
    object Failure: OrderState()
}