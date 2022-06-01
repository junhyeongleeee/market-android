package com.example.shopping.presentation.detail.navigation.order

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.model.product.ProductModel

sealed class OrderState {
    object UnInitialized: OrderState()
    object Loading: OrderState()
    data class Success(
        val productModel: ProductModel
    ): OrderState()
    object Failure: OrderState()
}