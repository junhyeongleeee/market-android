package com.example.shopping.domain.repository

import androidx.paging.PagingData
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.response.product.ProductResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepositoryImpl {

    suspend fun getCategories(): List<CategoryEntity>

    // Paging3
    fun getCategoryByProduct(query: String): Flow<PagingData<ProductResponse>>
}