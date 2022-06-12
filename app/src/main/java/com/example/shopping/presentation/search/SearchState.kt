package com.example.shopping.presentation.search

sealed class SearchState {
    object UnInitialized: SearchState()
    object Loading: SearchState()
    object Success: SearchState()
    object Failure: SearchState()
}