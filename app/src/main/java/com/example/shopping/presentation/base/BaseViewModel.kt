package com.example.shopping.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.presentation.RemoteState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel: ViewModel() {


    private val _fetchState = MutableLiveData<Pair<Throwable, RemoteState>>()
    val fetchState: LiveData<Pair<Throwable, RemoteState>>
        get() = _fetchState

    open val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

        when (throwable) {
            is SocketException -> _fetchState.postValue(Pair(throwable, RemoteState.BAD_INTERNET))
            is HttpException -> _fetchState.postValue(Pair(throwable, RemoteState.PARSE_ERROR))
            is UnknownHostException -> _fetchState.postValue(Pair(throwable,
                RemoteState.WRONG_CONNECTION))
            else -> _fetchState.postValue(Pair(throwable, RemoteState.FAIL))
        }
    }

    abstract fun fetch(): Job
}