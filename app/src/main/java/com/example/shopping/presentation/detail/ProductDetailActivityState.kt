package com.example.shopping.presentation.detail

sealed class ProductDetailActivityState {
    object UnInitialized: ProductDetailActivityState()
    object Loading: ProductDetailActivityState()
    object Success: ProductDetailActivityState()
    object Failure: ProductDetailActivityState()
}