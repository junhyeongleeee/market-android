package com.example.shopping.model.category

import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.Model

data class CategoryModel(
    override val id: Long,
    override val type: CellType = CellType.CATEGORY_CELL,
    val category_id: String,
    val name: String,
    val image_url: String = ""
): Model(id, type)
