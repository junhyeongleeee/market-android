package com.example.shopping.data.repository

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
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
                userName = userName,
                email = email,
                password = password,
                phone = phone
            )
        ).body()?: null
    }
}