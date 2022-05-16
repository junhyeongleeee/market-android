package com.example.shopping.domain.usecases

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.repository.CategoryRepository

class GetAllCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<CategoryEntity>{
        return categoryRepository.getCategories()
    }
}