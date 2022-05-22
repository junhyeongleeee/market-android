package com.example.shopping.viewModelTest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.di.module.appTestModule
import com.example.shopping.liveData.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
internal abstract class MockServerTest{

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var context: Application

    protected lateinit var server: MockWebServer
    private lateinit var client: OkHttpClient
    private lateinit var retrofit: Retrofit
    protected lateinit var service: ApiService

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        server = MockWebServer()
        server.start()

        createClient()
        createRetrofit()
        createService()
    }

    @After
    fun tearDown() {
        server.shutdown()
        Dispatchers.resetMain() // MainDispatcher 를 초기화 해주어야 메모리 누수가 발생하지 않는다.
        dispatcher.cleanupTestCoroutines()
    }

    private fun createClient(){
        client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private fun createRetrofit(){
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/v1/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun createService(){
        service = retrofit.create(ApiService::class.java)
    }

    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver
    }
}