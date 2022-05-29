package com.example.shopping.domain.repository.user

import com.example.shopping.data.response.user.UserResponse

interface UserRepositoryImpl {
    suspend fun createUser(userName: String, email: String, name: String, phone: String?) : UserResponse?
}