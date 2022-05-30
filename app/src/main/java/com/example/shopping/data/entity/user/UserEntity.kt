package com.example.shopping.data.entity.user

import com.example.shopping.model.type.UserType

data class UserEntity(
    val _id: String,
    val uid: String,
    val username: String,
    val email: String,
    val phone: String? = null,
    val role: UserType = UserType.Customer
)
