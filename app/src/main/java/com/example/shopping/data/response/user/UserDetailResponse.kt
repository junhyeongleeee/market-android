package com.example.shopping.data.response.user

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.data.entity.user.UserEntity
import com.example.shopping.model.type.UserType

data class UserDetailResponse(
    val uid: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val type: UserType = UserType.Customer
){
    fun toEntity() = UserDetailEntity(
        uid, name, email, phone, type
    )
}
