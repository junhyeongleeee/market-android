package com.example.shopping.data.repository.search

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.domain.repository.search.SearchRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : SearchRepositoryImpl{
    override suspend fun getSearchProducts(keyword: String): List<ProductSimpleEntity> = withContext(ioDispatcher) {
        val response = apiService.getSearchProducts(keyword)

        if(response.isSuccessful){
            response?.body()?.products ?: listOf()
        }else{
            listOf()
        }
    }
}