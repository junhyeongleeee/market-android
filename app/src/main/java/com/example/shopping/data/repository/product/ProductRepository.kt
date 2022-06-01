package com.example.shopping.data.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity
import com.example.shopping.data.entity.product.order.OrderEntity
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
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

    override suspend fun orderProduct(access_token: String, orderList: List<OrderItemEntity>): OrderEntity? = withContext(ioDispatcher){
        val response = apiService.orderProduct(access_token, orderList)

        if(response.isSuccessful){
            response?.body()?.toEntity() ?: null
        }
        else null
    }
}