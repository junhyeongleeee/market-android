package com.example.shopping.data.response.product

import com.example.shopping.data.entity.product.ProductSimpleEntity

data class ProductResponse(
    val total: String,
    val products: List<ProductSimpleEntity>
)