package com.example.shopping.presentation.my.userDetail

import com.example.shopping.presentation.main.MainState

sealed class UserDetailState {
    object UnInitialized: UserDetailState()
    object Loading: UserDetailState()
    object Success: UserDetailState()
    object Failure: UserDetailState()
}