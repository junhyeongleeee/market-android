package com.example.shopping.domain.repository.search

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity

interface SearchRepositoryImpl {
    suspend fun getSearchProducts(keyword: String): List<ProductSimpleEntity>
}