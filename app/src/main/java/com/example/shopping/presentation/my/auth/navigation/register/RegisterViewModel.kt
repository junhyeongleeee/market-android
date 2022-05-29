package com.example.shopping.presentation.my.auth.navigation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.auth.navigation.login.LoginState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(
//    private val userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel() {

    private var _registerStateLiveData = MutableLiveData<RegisterState>(RegisterState.UnInitialized)
    val registerStateLiveData: LiveData<RegisterState> = _registerStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _registerStateLiveData.postValue(RegisterState.Loading)
    }

    fun createUser(userName: String, email: String, password: String, phone: String?) = viewModelScope.launch{
        // TODO Preference 계정 추가

        _registerStateLiveData.postValue(RegisterState.Success(
            UserEntity("j1", "1", "이준형" ,"djskal3745@gmail.com", "01036153247")
        ))

        /*try {
            userRepositoryImpl.createUser(userName, email, password, phone)?.let {
                _registerStateLiveData.postValue(RegisterState.Success(it.toEntity()))
            } ?: kotlin.run { _registerStateLiveData.postValue(RegisterState.Failure) }

        }catch (e: Exception){
            e.printStackTrace()
            _registerStateLiveData.postValue(RegisterState.Failure)
        }*/
    }
}