package com.example.shopping.presentation.my.auth.navigation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.presentation.RemoteState
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.auth.navigation.login.LoginState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class RegisterViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
) : BaseViewModel() {

    private var _registerStateLiveData = MutableLiveData<RegisterState>(RegisterState.UnInitialized)
    val registerStateLiveData: LiveData<RegisterState> = _registerStateLiveData

    private val _fetchState = MutableLiveData<Pair<Throwable, RemoteState>>()
    val fetchState: LiveData<Pair<Throwable, RemoteState>>
        get() = _fetchState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

        when (throwable) {
            is SocketException -> _fetchState.postValue(Pair(throwable, RemoteState.BAD_INTERNET))
            is HttpException -> _fetchState.postValue(Pair(throwable, RemoteState.PARSE_ERROR))
            is UnknownHostException -> _fetchState.postValue(Pair(throwable,
                RemoteState.WRONG_CONNECTION))
            else -> _fetchState.postValue(Pair(throwable, RemoteState.FAIL))
        }
    }

    override fun fetch(): Job = viewModelScope.launch {

    }

    fun createUser(userName: String, email: String, password: String, phone: String?) =
        viewModelScope.launch(exceptionHandler) {

            _registerStateLiveData.postValue(RegisterState.Loading)

            userRepositoryImpl.createUser(userName, email, password, phone)?.let {
                _registerStateLiveData.postValue(RegisterState.Success(it.toEntity()))
            } ?: kotlin.run { _registerStateLiveData.postValue(RegisterState.Failure) }
        }
}