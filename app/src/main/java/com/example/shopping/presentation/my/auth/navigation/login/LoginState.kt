package com.example.shopping.presentation.my.auth.navigation.login

sealed class LoginState {
    object UnInitialized: LoginState()
    object Loading: LoginState()
    object Success: LoginState()
    object Failure: LoginState()
}