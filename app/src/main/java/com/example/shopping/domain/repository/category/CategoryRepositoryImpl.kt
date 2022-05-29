package com.example.shopping.domain.repository.category

import androidx.paging.PagingData
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.response.product.ProductResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepositoryImpl {

    suspend fun getCategories(): List<CategoryEntity>

    suspend fun getProductsByCategory(category_id: String): List<ProductResponse>

    // Paging3
    fun getCategoryByProduct(query: String): Flow<PagingData<ProductResponse>>
}