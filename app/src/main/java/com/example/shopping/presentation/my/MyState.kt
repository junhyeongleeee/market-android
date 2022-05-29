package com.example.shopping.presentation.my

import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.main.MainState

sealed class MyState {
    object UnInitialized: MyState()
    object Loading: MyState()
    data class Success(
        val userDetailEntity: UserDetailEntity?
    ): MyState()
    object Failure: MyState()
}