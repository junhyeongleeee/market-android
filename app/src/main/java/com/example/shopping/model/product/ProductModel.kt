package com.example.shopping.model.product

import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.ProductEntity

data class ProductModel(
    override val id: Long,
    override val type: CellType = CellType.PRODUCT_CELL,
    val uid: String,
    val name: String,
    val price: Long,
    val image_url: String,
): Model(id, type) {
    fun toEntity() = ProductEntity(
        uid = uid,
        name = name,
        price = price,
        image_url = image_url
    )
}