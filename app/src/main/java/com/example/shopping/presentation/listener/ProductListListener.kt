package com.example.shopping.presentation.listener

import com.example.shopping.model.recyclerView.product.ProductModel

interface ProductListListener: AdapterListener{
    fun onClickItem(model: ProductModel)
}