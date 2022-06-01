package com.example.shopping.presentation.detail.navigation.productDetail

import com.example.shopping.model.category.CategoryModel

sealed class ProductDetailState {
    object UnInitialized: ProductDetailState()
    object Loading: ProductDetailState()
    data class Success(
        val list : List<CategoryModel>
    ): ProductDetailState()
    object Failure: ProductDetailState()
}