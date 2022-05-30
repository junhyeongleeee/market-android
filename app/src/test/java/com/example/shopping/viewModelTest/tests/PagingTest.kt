package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.entity.product.ProductSimpleEntity
import com.example.shopping.viewModelTest.MockServerTest
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

internal class PagingTest: MockServerTest() {

    private lateinit var mockCategoryList: List<CategoryEntity>
    private lateinit var mockProductSimpleList: List<ProductSimpleEntity>


    @Before
    fun initData(){
        mockCategoryList = (0 until 50).map {
            CategoryEntity(
                uid = it.toString(),
                name = "name : $it"
            )
        }

        mockProductSimpleList = (0 until 50).map {
            ProductSimpleEntity(
                uid = it.toString(),
                name = "product $it",
                price = (it*1000).toLong(),
                image_url = "url \"../$it/"
            )
        }

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(mockProductSimpleList))

        server.enqueue(response)
    }

    @Test
    fun `Test Get Category By Products`() = runBlocking {

        val res = service.getCategoryByProducts(
            category_id = "0",
            page = 1,
            take = 20
        )

        if(res.isSuccessful){
            val entities = res?.body()

            entities?.let {
                assert(it.productSimples[0].uid == "0")
            }
        }
    }

    @Test
    fun `Test Paging`() = runBlocking {

    }
}