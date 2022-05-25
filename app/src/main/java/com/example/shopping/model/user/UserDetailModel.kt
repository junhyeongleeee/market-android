package com.example.shopping.model.user

import aop.fastcampus.part6.chapter01.model.UserType

data class UserDetailModel(
    val uid: String,
    val userName: String,
    val email: String,
    val phone: String?,
    val role: UserType = UserType.Customer
)
