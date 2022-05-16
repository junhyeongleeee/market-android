package com.example.shopping.presentation.category

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.presentation.main.MainState

sealed class CategoryState {
    object UnInitialized: CategoryState()
    object Loading: CategoryState()
    data class Success(
        val list : List<CategoryEntity>
    ): CategoryState()
    object Failure: CategoryState()
}