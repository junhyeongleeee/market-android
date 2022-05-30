package com.example.shopping.data.repository.home

import android.util.Log
import com.example.shopping.data.entity.home.RecommendEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.domain.repository.home.HomeRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class HomeRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : HomeRepositoryImpl {

    override suspend fun getHome(): List<RecommendEntity> = withContext(ioDispatcher){

        val response = apiService.getHome()

        Log.e("GetHome", response.errorBody()?.string() ?: "")

        if(response.isSuccessful){
            response.body()?.mapIndexed { _, response ->
                response.toEntity()
            } ?: listOf()
        }
        else{
            Log.e("GetHome", response.errorBody()?.string() ?: "")
            listOf()
        }
    }
}