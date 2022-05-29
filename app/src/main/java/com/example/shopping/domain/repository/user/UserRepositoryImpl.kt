package com.example.shopping.domain.repository.user

import com.example.shopping.data.response.user.LoginResponse
import com.example.shopping.data.response.user.UserDetailResponse
import com.example.shopping.data.response.user.UserResponse

interface UserRepositoryImpl {
    suspend fun createUser(
        userName: String,
        email: String,
        name: String,
        phone: String?,
    ): UserResponse?

    suspend fun signInUser(
        email: String,
        password: String
    ): LoginResponse?

    suspend fun signOutUser(
        access_token: String
    ): String?

    suspend fun getUserDetail(
        access_token: String
    ): UserDetailResponse?
}