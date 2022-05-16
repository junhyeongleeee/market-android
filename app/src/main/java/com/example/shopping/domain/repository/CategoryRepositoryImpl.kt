package com.example.shopping.domain.repository

import com.example.shopping.data.entity.category.CategoryEntity

interface CategoryRepositoryImpl {
    suspend fun getCategories(): List<CategoryEntity>
}