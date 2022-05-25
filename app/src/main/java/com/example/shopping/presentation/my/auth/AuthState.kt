package com.example.shopping.presentation.my.auth

sealed class AuthState {
    object UnInitialized: AuthState()
    object Loading: AuthState()
    object Success: AuthState()
    object Failure: AuthState()
}