package com.example.shopping.presentation.listener.order

import com.example.shopping.model.recyclerView.product.order.OrderModel
import com.example.shopping.presentation.listener.AdapterListener

interface OrderListListener : AdapterListener {
    fun onCancelButton(model: OrderModel)
    fun onRepurchaseButton(model: OrderModel)
    fun onRefundButtonButton(model: OrderModel)
    fun onCanceledDetailButton(model: OrderModel)
    fun onOrderDeleteButton(model: OrderModel)
}