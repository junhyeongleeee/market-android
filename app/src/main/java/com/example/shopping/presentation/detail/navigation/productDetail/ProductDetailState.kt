package com.example.shopping.presentation.detail.navigation.productDetail

import com.example.shopping.model.recyclerView.product.ProductModel

sealed class ProductDetailState {
    object UnInitialized: ProductDetailState()
    object Loading: ProductDetailState()
    data class Success(
        val productModel: ProductModel
    ): ProductDetailState()
    object Failure: ProductDetailState()
}