package com.example.shopping.model.user

import com.example.shopping.model.type.UserType

data class UserDetailModel(
    val uid: String,
    val userName: String,
    val email: String,
    val phone: String?,
    val role: UserType = UserType.Customer
)
