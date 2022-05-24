package com.example.shopping.data.response.categoty

import com.example.shopping.data.entity.category.CategoryEntity

data class CategoryResponse(
    val id: String,
    val name: String,
    val image_url: String = ""
){
    fun toEntity() = CategoryEntity(
        id = id,
        name = name,
        image_url = image_url
    )
}