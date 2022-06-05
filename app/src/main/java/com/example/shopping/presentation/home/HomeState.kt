package com.example.shopping.presentation.home

import com.example.shopping.model.recyclerView.product.ProductModel

sealed class HomeState {
    object UnInitialized: HomeState()
    object Loading: HomeState()
    data class Success(
        val whatList : List<ProductModel>,
        val hotList : List<ProductModel>
    ): HomeState()
    object Failure: HomeState()
}