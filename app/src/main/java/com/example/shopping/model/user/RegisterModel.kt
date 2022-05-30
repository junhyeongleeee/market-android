package com.example.shopping.model.user

data class RegisterModel(
    val username: String,
    val email: String,
    val password: String,
    val phone : String?
)
