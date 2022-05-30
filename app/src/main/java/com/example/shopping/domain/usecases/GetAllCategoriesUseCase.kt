package com.example.shopping.domain.usecases

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl

class GetAllCategoriesUseCase(
    private val categoryRepository: CategoryRepositoryImpl
) {
    suspend operator fun invoke(): List<CategoryEntity>{
        return categoryRepository.getCategories()
    }
}