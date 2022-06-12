package com.example.shopping.presentation.search.navigation.products

sealed class SearchResultState {
    object UnInitialized: SearchResultState()
    object Loading: SearchResultState()
    object Success: SearchResultState()
    object Failure: SearchResultState()
}