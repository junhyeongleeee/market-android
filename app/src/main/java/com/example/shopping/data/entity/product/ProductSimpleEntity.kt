package com.example.shopping.data.entity.product

import com.example.shopping.model.recyclerView.product.ProductModel

data class ProductSimpleEntity(
    val uid: String,
    val name: String,
    val price: Int,
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