package com.example.shopping.model.recyclerView.product.order

import android.os.Parcelable
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.type.CellType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderModel(
    override val id: Long,
    override val type: CellType = CellType.ORDER_CELL,
    val uid: String,
    val customer_id: String,
    val total_price: Int,
    val status: String,
    val ordered_at: String,
    val updated_at: String,
    val items: List<OrderItemEntity>
): Model(id, type), Parcelable
