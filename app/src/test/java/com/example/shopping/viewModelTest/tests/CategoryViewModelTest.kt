package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.response.categoty.CategoryResponse
import com.example.shopping.presentation.category.CategoryState
import com.example.shopping.viewModelTest.MockServerTest
import com.example.shopping.viewModelTest.viewModel.TestCategoryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class CategoryViewModelTest : MockServerTest() {

    private lateinit var list: List<CategoryResponse>

    @Before
    fun init() {
        initData()
    }

    private fun initData(){

        list = (0 until 10).map {
            CategoryResponse(
                id = it.toString(),
                name = "name $it"
            )
        }

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(list))

        server.enqueue(response)
    }

    @Test
    fun `test get categories`() = runBlockingTest{

        val testCategoryViewModelTest = TestCategoryViewModel(service)
        val testObservable = testCategoryViewModelTest.mainStateLiveData.test()

        testCategoryViewModelTest.fetch()

        val result = list.mapIndexed { _, it ->  it.toEntity().toModel()}

        testObservable.assertValueSequence(
            listOf(
                CategoryState.UnInitialized,
                CategoryState.Loading,
                CategoryState.Failure
            )
        )
    }
}