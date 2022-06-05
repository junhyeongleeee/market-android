package com.example.shopping.model.recyclerView.product.order

import android.os.Parcelable
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.model.type.CellType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefundModel(
    override val id: Long,
    override val type: CellType = CellType.REFUND_CELL,
    val order: OrderModel,
    val customer_id: String,
    val status: String,
    val reason: String?,
    val created_at: String,
    val updated_at: String,
): Model(id, type), Parcelable
