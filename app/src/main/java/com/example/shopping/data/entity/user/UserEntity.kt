package com.example.shopping.data.entity.user

import com.example.shopping.model.type.UserType

data class UserEntity(
    val _id: String,
    val uid: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val type: UserType = UserType.Customer
)
