package com.example.shopping.domain.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.model.product.order.OrderRequestListModel

interface ProductRepositoryImpl {
    suspend fun getProductDetail(product_id: String) : ProductDetailEntity?
    suspend fun orderProduct(access_token: String, orderRequestList: OrderRequestListModel) : OrderEntity?
    suspend fun getOrderList(access_token: String) : List<OrderEntity>
}