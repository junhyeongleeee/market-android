package com.example.shopping.data.entity.user

import com.example.shopping.model.type.UserType

data class UserDetailEntity(
    val uid: String,
    val userName: String,
    val email: String,
    val phone: String? = null,
    val role: UserType = UserType.Customer
)
