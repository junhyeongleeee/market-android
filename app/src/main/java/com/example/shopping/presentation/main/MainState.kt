package com.example.shopping.presentation.main

sealed class MainState {
    object UnInitialized: MainState()
    data class SelectedSuccess(
        val position: Int
    ): MainState()
    object SelectedFailure: MainState()
    data class BackPressedSuccess(
        val item: Int
    ): MainState()
    object BackPressedFailure: MainState()
}