package com.example.shopping.data.entity.product

import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.product.home.ProductRecommendModel

data class ProductEntity(
    val _id: String,
    val uid: String,
    val category_id: String,
    val name: String,
    val price: Int,
    val description: String?,
    val image_url: String?
) {
    fun toRecommendModel() = ProductRecommendModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        category_id = category_id,
        name = name,
        price = price,
        description = description,
        image_url = image_url
    )

    fun toProductModel() = ProductModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        name = name,
        price = price,
        image_url = image_url
    )
}