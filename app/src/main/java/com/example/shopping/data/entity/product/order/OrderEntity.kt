package com.example.shopping.data.entity.product.order

import android.os.Parcelable
import com.example.shopping.model.recyclerView.product.order.OrderModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderEntity(
    val uid: String,
    val customer_id: String,
    val total_price: Int,
    val status: String,
    val ordered_at: String,
    val updated_at: String,
    val items: List<OrderItemEntity>,
): Parcelable {
    fun toModel() = OrderModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        customer_id = customer_id,
        total_price = total_price,
        status = status,
        ordered_at = ordered_at,
        updated_at = updated_at,
        items = items
    )
}
