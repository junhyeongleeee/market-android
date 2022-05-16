package com.example.shopping.data.repository

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
) : CategoryRepositoryImpl {
    override suspend fun getCategories(): List<CategoryEntity> = withContext(ioDispatcher) {
        val response = apiService.getAllCategory()

        if (response.isSuccessful) {
            response?.body()?.mapIndexed { index, it ->
                CategoryEntity(
                    id = it.id,
                    name = it.id
                )
            } ?: listOf()
        } else listOf()
    }
}