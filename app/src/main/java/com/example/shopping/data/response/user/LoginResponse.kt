package com.example.shopping.data.response.user

import com.example.shopping.model.user.UserDetailModel

data class LoginResponse (
    val access_token: String,
    val userDetailModel: UserDetailModel
)