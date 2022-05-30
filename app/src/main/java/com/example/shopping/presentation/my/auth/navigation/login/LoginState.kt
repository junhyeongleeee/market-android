package com.example.shopping.presentation.my.auth.navigation.login

import com.example.shopping.model.user.UserDetailModel

sealed class LoginState {
    object UnInitialized: LoginState()
    object Loading: LoginState()
    data class Success(
        private val userDetailModel: UserDetailModel
    ): LoginState()
    object Failure: LoginState()
}