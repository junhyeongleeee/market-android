package com.example.shopping.data.entity.product.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class OrderItemEntity (
    val uid: String,
    val order_id: String,
    val product_id: String,
    val product_name: String,
    val product_price: Integer,
    val count: Integer
)