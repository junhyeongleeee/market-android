package com.example.shopping.model.remote.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderRequestModel (
    val product_id: String,
    val count: Int,
) : Parcelable