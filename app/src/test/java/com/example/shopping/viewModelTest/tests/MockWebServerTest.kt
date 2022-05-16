package com.example.shopping.viewModelTest.tests

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.di.module.appTestModule
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MockWebServerTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var context: Application


    private lateinit var server: MockWebServer
    private lateinit var client: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService

    private lateinit var mockList: List<CategoryEntity>

    @Before
    fun setUp() {

        server = MockWebServer()
        server.start()

        createClient()
        createRetrofit()
        createService()

        Dispatchers.setMain(dispatcher)

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
            .setBody(Gson().toJson(mockList))

        server.enqueue(response)
    }

    @After
    fun tearDown() {
        server.shutdown()
        Dispatchers.resetMain() // MainDispatcher 를 초기화 해주어야 메모리 누수가 발생하지 않는다.
        dispatcher.cleanupTestCoroutines()
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


    private fun createClient() {
        client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
    private fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/v1/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    private fun createService() {
        service = retrofit.create(ApiService::class.java)
    }
}
