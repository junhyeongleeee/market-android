package com.example.shopping.presentation.my.auth.navigation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.data.response.user.LoginResponse
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.type.UserType
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.MyState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val preference: AppPreferenceManager
): BaseViewModel() {

    private var _loginStateLiveData = MutableLiveData<LoginState>(LoginState.UnInitialized)
    val loginStateLiveData: LiveData<LoginState> = _loginStateLiveData

    override fun fetch(): Job = viewModelScope.launch {

    }

    fun login(email: String, pwd: String) = viewModelScope.launch{
        _loginStateLiveData.postValue(LoginState.Loading)

        try {
            userRepositoryImpl.signInUser(email, pwd)?.let {
                _loginStateLiveData.postValue(LoginState.Success(
                    it.user
                ))

                withContext(Dispatchers.IO){
                    preference.setString("access_token", it.access_token)
                }

            }?: kotlin.run {
                _loginStateLiveData.postValue(LoginState.Failure)
            }
        }catch (e: Exception){
            e.printStackTrace()
            _loginStateLiveData.postValue(LoginState.Failure)
        }

        /*_loginStateLiveData.postValue(LoginState.Success(
            UserDetailModel(
                uid = "1",
                userName = "이준형",
                email = "djskal3745@gail.com",
                phone = "01036153247",
                role = UserType.Customer
            )
        ))*/

    }
}