package com.example.shopping.viewModelTest.tests

import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.model.type.UserType
import com.example.shopping.presentation.my.auth.navigation.register.RegisterState
import com.example.shopping.viewModelTest.MockServerTest
import com.example.shopping.viewModelTest.viewModel.user.TestRegisterViewModel
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class RegisterViewModelTest: MockServerTest() {

    private lateinit var userResponse: UserResponse

    @Before
    fun init(){
        initData()
    }

    private fun initData(){

        userResponse = UserResponse(
            _id = "12312312",
            uid = "213",
            name = "이준형",
            email = "djskal3745@gmail.com",
            phone = "010-3615-3247",
            type = UserType.Customer
        )

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(Gson().toJson(userResponse))

        server.enqueue(response)
    }

    @Test
    fun `Test CreateUser in RegisterViewModel`(){

        val testRegisterViewModel = TestRegisterViewModel(service)
        val testRegisterObservable = testRegisterViewModel.registerStateLiveData.test()

        testRegisterViewModel.fetch()

        testRegisterObservable.assertValueSequence(
            listOf(
                RegisterState.UnInitialized,
                RegisterState.Loading,
                RegisterState.Success(userResponse.toEntity())
            )
        )

    }
}