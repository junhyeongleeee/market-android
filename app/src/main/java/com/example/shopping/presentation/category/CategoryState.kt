package com.example.shopping.presentation.category

import com.example.shopping.model.recyclerView.category.CategoryModel

sealed class CategoryState {
    object UnInitialized: CategoryState()
    object Loading: CategoryState()
    data class Success(
        val list : List<CategoryModel>
    ): CategoryState()
    object Failure: CategoryState()
}