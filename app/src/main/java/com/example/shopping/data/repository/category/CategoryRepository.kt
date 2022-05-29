package com.example.shopping.data.repository.category

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.util.ExamplePagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
) : CategoryRepositoryImpl {
    override suspend fun getCategories(): List<CategoryEntity> = withContext(ioDispatcher) {
        val response = apiService.getAllCategory()

        if (response.isSuccessful) {
            response?.body()?.mapIndexed { index, it ->
                CategoryEntity(
                    id = it.id,
                    name = it.id
                )
            } ?: listOf()
        } else listOf()
    }

    override suspend fun getProductsByCategory(category_id: String): List<ProductResponse> {
        TODO("Not yet implemented")
    }

    // Paging3
    override fun getCategoryByProduct(page: String): Flow<PagingData<ProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,                      // TODO : pageSize, 각 페이지에 로드할 데이터 수
                enablePlaceholders = false          // TODO : enablePlaceholders, 플레이스 홀더 여부
            ),
            pagingSourceFactory = { ExamplePagingSource(apiService, page) }    // TODO : PagingSource 인스턴스 생성
        ).flow
    }
}