package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.model.type.UserType
import com.example.shopping.model.user.RegisterModel
import com.example.shopping.viewModelTest.MockServerTest
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class MockWebServerTest: MockServerTest() {

    private lateinit var mockList: List<CategoryEntity>

    @Before
    fun init() {
        initData()
    }

    private fun initData() {
        mockList = (0 until 10).map {
            CategoryEntity(
                id = it.toString(),
                name = "name : $it"
            )
        }

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(
                UserResponse(
                    _id = "12312312",
                    uid = "213",
                    name = "이준형",
                    email = "djskal3745@gmail.com",
                    phone = "010-3615-3247",
                    type = UserType.Customer
                )
            ))

        server.enqueue(response)

    }

    @Test
    fun `Test MockServer`() = runBlocking {

        val res = service.getAllCategory()

        if(res.isSuccessful){
            val entities = res?.body()

            entities?.let {
                assert(it[0].id == "0")
            }
        }
    }

    @Test
    fun `Test Post`() = runBlocking{

        val res = service.createUser(
            RegisterModel(
                userName = "이준형",
                email = "djskal3745@gmail.com",
                password = "w4d6a8",
                phone = "010-3615-3247"
            )
        )

        if(res.isSuccessful){
            val entities = res?.body()

            entities?.let{

            }
        }
    }
}
