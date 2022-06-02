package com.example.shopping.data.entity.product.order

import android.os.Parcelable
import com.example.shopping.model.product.order.OrderItemModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItemEntity (
    val uid: String,
    val order_id: String,
    val product_id: String,
    val product_name: String,
    val product_price: Int,
    val count: Int
): Parcelable
{
    fun toModel() = OrderItemModel(
        id = uid.hashCode().toLong(),
        uid = uid,
        order_id = order_id,
        product_id = product_id,
        product_name = product_name,
        product_price = product_price,
        count = count
    )
}