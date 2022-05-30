package com.example.shopping.data.response.home

import com.example.shopping.data.entity.home.RecommendEntity
import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity

data class RecommendResponse(
    val title: String,
    val products: List<ProductSimpleEntity>
){
    fun toEntity() = RecommendEntity(title, products)
}
