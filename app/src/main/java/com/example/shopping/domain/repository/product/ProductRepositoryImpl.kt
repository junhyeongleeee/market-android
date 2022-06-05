package com.example.shopping.domain.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.RefundEntity
import com.example.shopping.model.remote.order.OrderRefundCancelModel
import com.example.shopping.model.recyclerView.product.order.OrderRequestListModel

interface ProductRepositoryImpl {
    suspend fun getProductDetail(product_id: String): ProductDetailEntity?
    suspend fun orderProduct(
        access_token: String,
        orderRequestList: OrderRequestListModel,
    ): OrderEntity?

    suspend fun getOrderList(access_token: String): List<OrderEntity>
    suspend fun getRefundList(access_token: String) : List<RefundEntity>

    suspend fun requestRefundCancel(
        access_token: String, order_id: String,
        requestRefundCancelModel: OrderRefundCancelModel,
    ): RefundEntity?

    suspend fun deleteOrder(access_token: String, order_id: String): OrderEntity?
    suspend fun deleteRefund(access_token: String, order_id: String): RefundEntity?
}
