package com.example.shopping.presentation.my.auth.navigation.register

import com.example.shopping.data.entity.user.UserEntity

sealed class RegisterState {
    object UnInitialized: RegisterState()
    object Loading: RegisterState()
    data class Success(
        val userEntity: UserEntity?
    ): RegisterState()
    object Failure: RegisterState()
}