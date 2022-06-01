package com.example.shopping.data.entity.user

import android.os.Parcelable
import com.example.shopping.model.type.UserType
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailEntity(
    val uid: String,
    val username: String,
    val email: String,
    val phone: String? = null,
    val role: String = UserType.Customer.type
): Parcelable
