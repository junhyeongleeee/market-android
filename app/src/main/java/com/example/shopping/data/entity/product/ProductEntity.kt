package com.example.shopping.data.entity.product

import com.example.shopping.model.product.ProductModel

data class ProductEntity(
    val uid: String,
    val name: String,
    val price: Long,
    val image_url: String?
) {
    fun toModel() = ProductModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        name = name,
        price = price,
        image_url = image_url
    )
}