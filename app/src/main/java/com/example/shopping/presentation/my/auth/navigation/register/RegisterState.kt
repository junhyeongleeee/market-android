package com.example.shopping.presentation.my.auth.navigation.register

sealed class RegisterState {
    object UnInitialized: RegisterState()
    object Loading: RegisterState()
    object Success: RegisterState()
    object Failure: RegisterState()
}