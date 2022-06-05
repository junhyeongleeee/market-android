package com.example.shopping.data.entity.product

import com.example.shopping.model.recyclerView.product.ProductModel

data class ProductDetailEntity(
    val uid: String,
    val name: String,
    val price: Int,
    val description: String? = null,
    val image_url: String? = null
) {
    fun toModel() = ProductModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        name = name,
        price = price,
        description = description,
        image_url = image_url
    )
}