package com.example.shopping.data.repository

import androidx.paging.PagingData
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import kotlinx.coroutines.flow.Flow

class TestCategoryRepository(
    private val service: ApiService
) : CategoryRepositoryImpl{

    override suspend fun getCategories(): List<CategoryEntity> {
        return service.getAllCategory()?.body()?.mapIndexed{ _, it ->
            CategoryEntity(
                id = it.id,
                name = it.name
            )
        } ?: listOf()
    }

    override suspend fun getProductsByCategory(category_id: String): List<ProductResponse> {
        return service.getCategoryByProducts(
            category_id = category_id
        )?.body()?: listOf()
    }

    override fun getCategoryByProduct(query: String): Flow<PagingData<ProductResponse>> {
        TODO("Not yet implemented")
    }
}