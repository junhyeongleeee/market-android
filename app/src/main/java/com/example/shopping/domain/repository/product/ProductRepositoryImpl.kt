package com.example.shopping.domain.repository.product

import com.example.shopping.data.entity.product.ProductDetailEntity

interface ProductRepositoryImpl {
    suspend fun getProductDetail(product_id: String) : ProductDetailEntity?
}