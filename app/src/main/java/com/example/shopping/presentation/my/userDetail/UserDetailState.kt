package com.example.shopping.presentation.my.userDetail

import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.presentation.main.MainState

sealed class UserDetailState {
    object UnInitialized: UserDetailState()
    object Loading: UserDetailState()
    data class Success(
        val userDetailEntity: UserDetailEntity
    ): UserDetailState()
    object Failure: UserDetailState()
}