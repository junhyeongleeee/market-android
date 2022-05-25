package com.example.shopping.extensions

import android.R
import android.net.Uri


fun getURLForResource(resId: Int): String? {
    return Uri.parse("android.resource://" + R::class.java.getPackage().name + "/" + resId)
        .toString()
}