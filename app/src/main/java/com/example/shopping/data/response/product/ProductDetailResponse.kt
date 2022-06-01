package com.example.shopping.data.response.product

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.ProductEntity

data class ProductDetailResponse(
    val uid: String,
    val name: String,
    val price: Long,
    val description: String? = null,
    val image_url: String? = null
){
    fun toEntity() = ProductDetailEntity(
        uid = uid,
        name = name,
        price = price,
        description = description,
        image_url = image_url
    )
}
