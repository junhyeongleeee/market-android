package com.example.shopping.presentation.search

import com.example.shopping.presentation.main.MainState

sealed class SearchState {
    object UnInitialized: SearchState()
}