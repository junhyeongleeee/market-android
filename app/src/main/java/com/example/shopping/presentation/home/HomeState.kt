package com.example.shopping.presentation.home

import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryState
import com.example.shopping.presentation.main.MainState

sealed class HomeState {
    object UnInitialized: HomeState()
    object Loading: HomeState()
    data class Success(
        val whatList : List<ProductModel>,
        val hotList : List<ProductModel>
    ): HomeState()
    object Failure: HomeState()
}