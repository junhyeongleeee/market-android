package com.example.shopping.data.repository.product

import android.util.Log
import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.RefundEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.model.remote.order.OrderRefundCancelModel
import com.example.shopping.model.recyclerView.product.order.OrderRequestListModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
): ProductRepositoryImpl {

    override suspend fun getProductDetail(product_id: String): ProductDetailEntity? = withContext(ioDispatcher){
        val response = apiService.getProductDetail(product_id)

        if(response.isSuccessful){
            response?.body()?.toEntity() ?: null
        }
        else null
    }

    override suspend fun orderProduct(access_token: String, orderRequestList: OrderRequestListModel): OrderEntity? = withContext(ioDispatcher){

        val token = "Bearer $access_token"
        val response = apiService.orderProduct(token, orderRequestList)

        if(response.isSuccessful){
            response?.body()?.toEntity() ?: null
        }
        else {
            Log.e("orderProduct", response.errorBody().toString())
            null
        }
    }

    override suspend fun getOrderList(access_token: String): List<OrderEntity> = withContext(ioDispatcher){

        val token = "Bearer $access_token"
        val response = apiService.getOrders(token)

        if(response.isSuccessful){
            response?.body()?.orders ?: listOf()
        }
        else{
            listOf()
        }
    }

    override suspend fun getRefundList(access_token: String): List<RefundEntity> = withContext(ioDispatcher){
        val token = "Bearer $access_token"
        val response = apiService.getRefunds(token)

        if(response.isSuccessful){
            response?.body()?.refunds?.mapIndexed { _, it ->
                it.toEntity()
            } ?: listOf()
        }
        else{
            listOf()
        }
    }

    override suspend fun requestRefundCancel(
        access_token: String,
        order_id: String,
        requestRefundCancelModel: OrderRefundCancelModel,
    ): RefundEntity? = withContext(ioDispatcher){

        val token = "Bearer $access_token"
        val response = apiService.requestRefundCancel(token, order_id, requestRefundCancelModel)

        if(response.isSuccessful){
            response?.body()?.toEntity() ?: null
        }
        else{
            null
        }
    }



}