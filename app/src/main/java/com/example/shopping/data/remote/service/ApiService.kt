package com.example.shopping.data.remote.service

import android.icu.util.ULocale
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.url.Url
import com.example.shopping.data.response.categoty.CategoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Url.PUT_CATEGORY)
    suspend fun putCategory(category: CategoryEntity)

    @GET(Url.GET_CATEGORY)
    suspend fun getAllCategory(): Response<List<CategoryResponse>>

}