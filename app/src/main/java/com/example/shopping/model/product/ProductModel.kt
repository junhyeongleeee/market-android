package com.example.shopping.model.product

import android.os.Parcelable
import com.example.shopping.model.type.CellType
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.ProductSimpleEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    override val id: Long,
    override val type: CellType = CellType.PRODUCT_OF_CATEGORY_CELL,
    val uid: String,
    val name: String,
    val price: Int,
    val description: String? = null,
    val image_url: String? = null,
): Model(id, type), Parcelable {
    fun toEntity() = ProductSimpleEntity(
        uid = uid,
        name = name,
        price = price,
        image_url = image_url
    )
}