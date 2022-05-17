package com.example.shopping.data.entity.category

import com.example.shopping.model.category.CategoryModel

data class CategoryEntity (
    val id: String,
    val name: String
){
    fun toModel() = CategoryModel(
        id = id,
        name = name
    )
}