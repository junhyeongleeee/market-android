package com.example.shopping.util.provider

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.example.shopping.util.provider.ResourcesProvider

class DefaultResourcesProvider(
    private val context: Context
) : ResourcesProvider {
    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg formArgs: Any): String = context.getString(resId, *formArgs)

    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getColorStateList(resId: Int): ColorStateList = context.getColorStateList(resId)
}