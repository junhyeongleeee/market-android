package com.example.shopping.presentation.category.productsByCategory

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.main.MainState

sealed class ProductsByCategoryState {
    object UnInitialized: ProductsByCategoryState()
    object Loading: ProductsByCategoryState()
    data class Success(
        val list : List<ProductModel>
    ): ProductsByCategoryState()
    object Failure: ProductsByCategoryState()
}