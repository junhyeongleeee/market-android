package com.example.shopping.data.entity.category

data class CategoryEntity (
    val id: String,
    val name: String,
){
    fun toEntity() = CategoryEntity(
        id,
        name
    )
}