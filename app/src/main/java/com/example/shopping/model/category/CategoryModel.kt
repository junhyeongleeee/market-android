package com.example.shopping.model.category

import com.example.shopping.model.type.CellType
import aop.fastcampus.part6.chapter01.model.Model

data class CategoryModel(
    override val id: Long,
    override val type: CellType = CellType.CATEGORY_CELL,
    val category_id: String,
    val name: String,
    val image_url: String? = null
): Model(id, type)
