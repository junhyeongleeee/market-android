package com.example.shopping.model.product.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderListModel (
    val items: List<OrderModel>
) : Parcelable