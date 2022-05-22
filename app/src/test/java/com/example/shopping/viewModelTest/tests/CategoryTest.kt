package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.entity.product.ProductEntity
import com.example.shopping.data.response.categoty.CategoryResponse
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.presentation.category.CategoryState
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryState
import com.example.shopping.viewModelTest.MockServerTest
import com.example.shopping.viewModelTest.viewModel.category.TestCategoryViewModel
import com.example.shopping.viewModelTest.viewModel.productByCategory.TestProductsByCategoryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class CategoryTest : MockServerTest() {

    private lateinit var categoryList: List<CategoryResponse>
    private lateinit var productList: List<ProductResponse>

    @Before
    fun init() {
        initData()
    }

    private fun initData(){

        categoryList = initCategories()
        productList = initProductsOfCategory()

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(productList))

        server.enqueue(response)
    }

    fun initCategories() = (0 until 10).map {
        CategoryResponse(
            id = it.toString(),
            name = "name $it"
        )
    }

    fun initProductsOfCategory() = (0 until 10).map {
        ProductResponse(
            uid = "$it",
            name = "name$it",
            price = (it*1000).toLong(),
            image_url = "/.../$it"
        )
    }

    @Test
    fun `test get categories`() = runBlockingTest{

        val testCategoryViewModelTest = TestCategoryViewModel(service)
        val testObservable = testCategoryViewModelTest.mainStateLiveData.test()

        testCategoryViewModelTest.fetch()

        val result = categoryList.mapIndexed { _, it ->  it.toEntity().toModel()}

        testObservable.assertValueSequence(
            listOf(
                CategoryState.UnInitialized,
                CategoryState.Loading,
                CategoryState.Failure
            )
        )
    }

    @Test
    fun `test get products of category`() = runBlockingTest {
        val testPbcViewModelTest = TestProductsByCategoryViewModel(service)
        val testObservable = testPbcViewModelTest.pbcStateLiveData.test()

        testPbcViewModelTest.fetch()
        testPbcViewModelTest.getCategories("1")

        testObservable.assertValueSequence(
            listOf(
                ProductsByCategoryState.UnInitialized,
                ProductsByCategoryState.Loading,
                ProductsByCategoryState.Success(productList.mapIndexed { _, it ->
                    ProductEntity(
                        uid = it.uid,
                        name = it.name,
                        price = it.price,
                        image_url = it.image_url
                    )
                })
            )
        )
    }
}