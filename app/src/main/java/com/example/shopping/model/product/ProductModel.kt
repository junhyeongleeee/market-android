package com.example.shopping.model.product

import com.example.shopping.data.entity.product.ProductEntity

class ProductModel(
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