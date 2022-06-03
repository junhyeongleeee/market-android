package com.example.shopping.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.type.UserType
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.RemoteState
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.alarm.AlarmState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class MyViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val preference: AppPreferenceManager
) : BaseViewModel() {

    private var _myStateLiveData = MutableLiveData<MyState>(MyState.UnInitialized)
    val myStateLiveData: LiveData<MyState> = _myStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        getUserData()
    }

    // TODO : User Data Fetching 필요
    private fun getUserData() = viewModelScope.launch(exceptionHandler){

        /*// Mock
        val userDetailModel = UserDetailEntity(
            uid = "1",
            userName = "이준형",
            email = "djskal3745@gmail.com",
            phone = "01036153247",
            role = UserType.Customer
        )*/

//        _myStateLiveData.postValue(MyState.Failure)

        /*_myStateLiveData.postValue(MyState.Success(
            userDetailModel
        ))*/

        _myStateLiveData.postValue(MyState.Loading)

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let{
            userRepositoryImpl.getUserDetail(it)?.let { response ->
                _myStateLiveData.postValue(MyState.Success(
                    response.toEntity()
                ))
            } ?: _myStateLiveData.postValue(MyState.Failure)
        } ?: _myStateLiveData.postValue(MyState.Failure)
    }
}