package com.example.shopping.data.entity.category

import com.example.shopping.model.category.CategoryModel

data class CategoryEntity (
    val id: String,
    val name: String,
    val image_url: String = ""
){
    fun toModel() = CategoryModel(
        id = id.toLong(),
        category_id = id,
        name = name,
        image_url = image_url
    )
}