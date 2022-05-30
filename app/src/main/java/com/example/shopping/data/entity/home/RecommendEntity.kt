package com.example.shopping.data.entity.home

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity

data class RecommendEntity(
    val title: String,
    val products: List<ProductSimpleEntity>
)
