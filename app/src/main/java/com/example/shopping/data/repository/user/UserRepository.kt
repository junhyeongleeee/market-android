package com.example.shopping.data.repository.user

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.user.RegisterModel
import kotlinx.coroutines.CoroutineDispatcher

class UserRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepositoryImpl{
    override suspend fun createUser(
        userName: String,
        email: String,
        password : String,
        phone: String?,
    ): UserResponse? = with(ioDispatcher){

        val response = apiService.createUser(
            RegisterModel(
                userName = userName,
                email = email,
                password = password,
                phone = phone
            )
        )

        if (response.isSuccessful) {
            response?.body() ?: null
        }
        else null
    }
}