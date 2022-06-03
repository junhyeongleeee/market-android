package com.example.shopping.presentation.listener

import com.example.shopping.model.category.CategoryModel
import com.example.shopping.model.product.order.OrderModel

interface OrderListListener : AdapterListener{
    fun onClickItem(model: OrderModel)
}