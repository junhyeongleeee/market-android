package com.example.shopping.domain.repository.home

import com.example.shopping.data.entity.home.RecommendEntity

interface HomeRepositoryImpl {
    suspend fun getHome(): List<RecommendEntity>
}