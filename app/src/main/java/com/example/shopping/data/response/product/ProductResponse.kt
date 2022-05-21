package com.example.shopping.data.response.product

import com.example.shopping.data.entity.product.ProductEntity

data class ProductResponse(
    val uid: String,
    val name: String,
    val price: Long,
    val image_url: String,
) {
    fun toEntity() = ProductEntity(
        uid = uid,
        name = name,
        price = price,
        image_url = image_url
    )
}