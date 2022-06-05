package com.example.shopping.model.recyclerView.product.order

import android.os.Parcelable
import com.example.shopping.model.remote.order.OrderRequestModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderRequestListModel (
    val items: List<OrderRequestModel>
) : Parcelable