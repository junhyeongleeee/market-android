package com.example.shopping.data.response.categoty

import com.example.shopping.data.entity.category.CategoryEntity

data class CategoryResponse(
    val total: Integer,
    val categories: List<CategoryEntity>
)