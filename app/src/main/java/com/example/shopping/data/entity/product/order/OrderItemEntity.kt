package com.example.shopping.data.entity.product.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItemEntity (
    val uid: String,
    val count: Int,
) : Parcelable