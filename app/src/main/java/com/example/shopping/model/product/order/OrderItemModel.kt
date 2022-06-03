package com.example.shopping.model.product.order

import android.os.Parcelable
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.type.CellType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItemModel(
    override val id: Long,
    override val type: CellType = CellType.ORDER_PRODUCT_CELL,
    val uid: String,
    val order_id: String,
    val product_id: String,
    val product_name: String,
    val product_price: Int,
    val product_image_url: String?,
    val count: Int
): Model(id, type), Parcelable
