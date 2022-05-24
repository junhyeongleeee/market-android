package com.example.shopping.presentation.listener

import android.widget.Adapter
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.model.product.ProductModel

interface ProductListListener: AdapterListener{
    fun onClickItem(model: ProductModel)
}