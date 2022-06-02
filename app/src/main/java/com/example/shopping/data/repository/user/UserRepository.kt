package com.example.shopping.data.repository.user

import android.util.Log
import android.widget.Toast
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.user.LoginResponse
import com.example.shopping.data.response.user.UserDetailResponse
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.user.LoginModel
import com.example.shopping.model.user.RegisterModel
import kotlinx.coroutines.CoroutineDispatcher

class UserRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepositoryImpl{

    override suspend fun createUser(
        username: String,
        email: String,
        password : String,
        phone: String?,
    ): UserResponse? = with(ioDispatcher){

        val response = apiService.createUser(
            RegisterModel(
                username = username,
                email = email,
                password = password,
                phone = phone
            )
        )

        if (response.isSuccessful) {
            response?.body() ?: null
        }
        else {
            Log.e("createUser", response.errorBody().toString())
            null
        }
    }

    override suspend fun signInUser(email: String, password: String): LoginResponse? = with(ioDispatcher){

        val response = apiService.signIn(
            LoginModel(
                email = email,
                password = password
            )
        )

        if(response.isSuccessful){
            response?.body() ?: null
        }
        else null

    }

    override suspend fun signOutUser(access_token: String): String? = with(ioDispatcher){
        val token = "Bearer $access_token"
        val response = apiService.signOut(token)

        if(response.isSuccessful){
            response?.body() ?: null
        }
        else null
    }

    override suspend fun getUserDetail(access_token: String): UserDetailResponse? = with(ioDispatcher){
        val token = "Bearer $access_token"
        val response = apiService.getUserDetail(token)

        if(response.isSuccessful){
            response?.body() ?: null
        }
        else null
    }
}