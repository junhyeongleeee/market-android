package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.presentation.category.CategoryState
import com.example.shopping.viewModelTest.MockServerTest
import com.example.shopping.viewModelTest.viewModel.TestCategoryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class CategoryViewModelTest : MockServerTest() {

    private lateinit var list: List<CategoryEntity>

    @Before
    fun init() {
        initData()
    }

    fun initData() = runBlockingTest{

        list = (0 until 10).map {
            CategoryEntity(
                id = it.toString(),
                name = "name : $it"
            )
        }

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(list))

        server.enqueue(response)
    }

    @Test
    fun `Test MockServer`() = runBlocking {

        val testCategoryViewModelTest = TestCategoryViewModel(service)

        val testObservable = testCategoryViewModelTest.mainStateLiveData.test()

        testCategoryViewModelTest.fetch()

        testObservable.assertValueSequence(
            listOf(
                CategoryState.UnInitialized,
                CategoryState.Loading,
                CategoryState.Success(list)
            )
        )
    }
}