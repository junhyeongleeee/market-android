package com.example.shopping.model.product.home

import com.example.shopping.model.type.CellType
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity

data class ProductRecommendModel(
    override val id: Long,
    override val type: CellType = CellType.PRODUCT_OF_CATEGORY_CELL,
    val uid: String,
    val category_id: String,
    val name: String,
    val price: Long,
    val description: String?,
    val image_url: String?,
): Model(id, type)