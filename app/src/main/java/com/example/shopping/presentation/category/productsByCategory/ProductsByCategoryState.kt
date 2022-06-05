package com.example.shopping.presentation.category.productsByCategory

import com.example.shopping.model.recyclerView.product.ProductModel

sealed class ProductsByCategoryState {
    object UnInitialized: ProductsByCategoryState()
    object Loading: ProductsByCategoryState()
    data class Success(
        val list : List<ProductModel>
    ): ProductsByCategoryState()
    object Failure: ProductsByCategoryState()
}