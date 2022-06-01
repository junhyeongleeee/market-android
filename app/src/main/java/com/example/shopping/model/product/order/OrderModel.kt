package com.example.shopping.model.product.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderModel (
    val product_id: String,
    val count: Int,
) : Parcelable