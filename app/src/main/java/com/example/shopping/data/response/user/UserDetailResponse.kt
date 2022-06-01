package com.example.shopping.data.response.user

import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.model.type.UserType

data class UserDetailResponse(
    val uid: String,
    val username: String,
    val email: String,
    val phone: String? = null,
    val type: String = UserType.Customer.type
){
    fun toEntity() = UserDetailEntity(
        uid, username, email, phone, type
    )
}
