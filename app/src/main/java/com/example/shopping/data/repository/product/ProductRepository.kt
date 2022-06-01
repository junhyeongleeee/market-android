package com.example.shopping.data.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity
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
}