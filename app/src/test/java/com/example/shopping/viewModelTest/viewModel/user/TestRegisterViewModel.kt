package com.example.shopping.viewModelTest.viewModel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.repository.TestCategoryRepository
import com.example.shopping.data.repository.TestUserRepository
import com.example.shopping.data.response.user.UserResponse
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.auth.navigation.login.LoginState
import com.example.shopping.presentation.my.auth.navigation.register.RegisterState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TestRegisterViewModel(
    private val service: ApiService
) : BaseViewModel() {

    private var _registerStateLiveData = MutableLiveData<RegisterState>(RegisterState.UnInitialized)
    val registerStateLiveData: LiveData<RegisterState> = _registerStateLiveData

    private var testUserRepository = TestUserRepository(service)

    override fun fetch(): Job = viewModelScope.launch {
        _registerStateLiveData.postValue(RegisterState.Loading)
        createUser("이준형", "djskal3745@gmail.com", "w4d6a8", "01036153247")
    }

    suspend fun createUser(userName: String, email: String, password: String, phone: String?) = viewModelScope.launch{

        val userEntity = testUserRepository.createUser(userName, email, password, phone)?.toEntity() ?: null

        userEntity?.let {
            _registerStateLiveData.postValue(RegisterState.Success(userEntity))
        }?: _registerStateLiveData.postValue(RegisterState.Failure)
    }
}