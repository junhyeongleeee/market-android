package com.example.shopping.data.response.user

import com.example.shopping.data.entity.user.UserEntity
import com.example.shopping.model.type.UserType

data class UserResponse(
    val _id: String,
    val uid: String,
    val username: String,
    val email: String,
    val phone: String? = null,
    val role: UserType = UserType.Customer
){
    fun toEntity() = UserEntity(
        _id = _id,
        uid = uid,
        username = username,
        email = email,
        phone = phone,
        role = role
    )
}
