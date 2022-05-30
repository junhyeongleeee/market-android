package com.example.shopping.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shopping.data.entity.product.ProductSimpleEntity
import com.example.shopping.data.remote.service.ApiService
import retrofit2.HttpException
import java.io.IOException


/**
 *  load (params: LoadParams<Key>
 *      - load 함수는 사용자가 스크롤 할 때마다 데이터를 비동기적으로 가져온다.
 *      - LoadParams : 로드 작업과 관련된 정보를 가지고 있다.
 *          - params.key 에 현재 페이지 인덱스를 관리한다.
 *          - params.loadSize 는 가져올 데이터 갯수를 관리한다.
 *      - load 함수는 LoadResult 를 반환한다.
 *          - LoadResult.Page : 로드에 성공한 후 , 데이터와 이전 다음 페이지 Key 가 포함된다.
 *              { data, prevKey, nextKey }
 *          - LoadResult.Error : 오류가 발생한 경우
 *
 */

class ExamplePagingSource(
    private val service: ApiService,
    private val category: String
) : PagingSource<Int, ProductSimpleEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ProductSimpleEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductSimpleEntity> {
        val page = params.key ?: 1

        return try {
            val response = service.getCategoryByProducts(
                category_id = category,
                page = page,
                take = params.loadSize
            )

            val repos = response?.body()
            repos?.let {
                LoadResult.Page(
                    data = it.products,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (it.products.isEmpty()) null else page + (params.loadSize / 10)
                )
            }?: kotlin.run {
                LoadResult.Error(IOException())
            }

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}