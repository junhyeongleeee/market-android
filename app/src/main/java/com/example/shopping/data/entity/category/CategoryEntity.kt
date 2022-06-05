package com.example.shopping.data.entity.category

import com.example.shopping.model.recyclerView.category.CategoryModel

data class CategoryEntity (
    val uid: String,
    val name: String,
    val image_url: String? = null
){
    fun toModel() = CategoryModel(
        id = uid.hashCode().toLong(),
        category_id = uid,
        name = name,
        image_url = image_url
    )
}