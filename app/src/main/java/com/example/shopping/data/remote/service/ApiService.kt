package com.example.shopping.data.remote.service

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.url.Url
import com.example.shopping.data.response.categoty.CategoryResponse
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.data.response.user.LoginResponse
import com.example.shopping.data.response.user.UserDetailResponse
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.model.user.LoginModel
import com.example.shopping.model.user.RegisterModel
import com.example.shopping.model.user.UserDetailModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(Url.PUT_CATEGORY)
    suspend fun putCategory(category: CategoryEntity)

    @GET(Url.GET_CATEGORY)
    suspend fun getAllCategory(): Response<CategoryResponse>

    @GET(Url.GET_PRODUCTS_BY_CATEGORY)
    suspend fun getCategoryByProducts(
        @Path("category_id") category_id: String,
        @Query("sort_type") sort_type: Int = 0,
        @Query("page") page: Int = 1,
        @Query("take") take: Int = 10
    ): Response<ProductResponse>

    @POST(Url.CREATE_USER)
    suspend fun createUser(
        @Body registerModel: RegisterModel
    ): Response<UserResponse>

    @POST(Url.SIGN_IN)
    suspend fun signIn(
        @Body loginModel: LoginModel
    ): Response<LoginResponse>

    @POST(Url.SIGN_OUT)
    suspend fun signOut(
        @Header("access_token") access_token: String
    ): Response<String>

    @POST(Url.GET_USER_DETAIL)
    suspend fun getUserDetail(
        @Header("access_token") access_token: String
    ): Response<UserDetailResponse>


}