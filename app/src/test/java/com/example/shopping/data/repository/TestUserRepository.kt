package com.example.shopping.data.repository

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.user.LoginResponse
import com.example.shopping.data.response.user.UserDetailResponse
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.user.RegisterModel
import kotlinx.coroutines.CoroutineDispatcher

class TestUserRepository(
    private val apiService: ApiService
) : UserRepositoryImpl{
    override suspend fun createUser(
        userName: String,
        email: String,
        password : String,
        phone: String?,
    ): UserResponse?{

        return apiService.createUser(
            RegisterModel(
                username = userName,
                email = email,
                password = password,
                phone = phone
            )
        ).body()?: null
    }

    override suspend fun signInUser(email: String, password: String): LoginResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun signOutUser(access_token: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetail(access_token: String): UserDetailResponse? {
        TODO("Not yet implemented")
    }
}