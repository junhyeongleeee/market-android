package com.example.shopping.presentation.detail

import com.example.shopping.model.category.CategoryModel

sealed class ProductDetailActivityState {
    object UnInitialized: ProductDetailActivityState()
    object Loading: ProductDetailActivityState()
    object Success: ProductDetailActivityState()
    object Failure: ProductDetailActivityState()
}