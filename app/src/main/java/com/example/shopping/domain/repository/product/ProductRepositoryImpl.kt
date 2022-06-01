package com.example.shopping.domain.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.product.order.OrderListModel
import com.example.shopping.model.product.order.OrderModel

interface ProductRepositoryImpl {
    suspend fun getProductDetail(product_id: String) : ProductDetailEntity?
    suspend fun orderProduct(access_token: String, orderList: OrderListModel) : OrderEntity?
}