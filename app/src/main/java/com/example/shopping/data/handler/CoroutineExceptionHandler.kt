package com.example.shopping.data.handler

import androidx.lifecycle.MutableLiveData
import com.example.shopping.presentation.RemoteState
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

class ExceptionHandler(
    val livedata: MutableLiveData<Pair<Throwable, RemoteState>>
) : CoroutineExceptionHandler {

    override val key: CoroutineContext.Key<*>
        get() = TODO("Not yet implemented")

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()

        when(exception){
            is SocketException -> livedata.postValue(Pair(exception, RemoteState.BAD_INTERNET))
            is HttpException -> livedata.postValue(Pair(exception, RemoteState.PARSE_ERROR))
            is UnknownHostException -> livedata.postValue(Pair(exception, RemoteState.WRONG_CONNECTION))
            else -> livedata.postValue(Pair(exception, RemoteState.FAIL))
        }
    }

}