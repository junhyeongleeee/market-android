package com.example.shopping.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.type.UserType
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.alarm.AlarmState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel(
    /*private val userRepositoryImpl: UserRepositoryImpl,
    private val preference: AppPreferenceManager*/
) : BaseViewModel() {

    private var _myStateLiveData = MutableLiveData<MyState>(MyState.UnInitialized)
    val myStateLiveData: LiveData<MyState> = _myStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _myStateLiveData.postValue(MyState.Loading)
        getUserData()
    }
    // TODO : User Data Fetching 필요
    private fun getUserData() = viewModelScope.launch{

        // Mock
        val userDetailModel = UserDetailEntity(
            uid = "1",
            userName = "이준형",
            email = "djskal3745@gmail.com",
            phone = "01036153247",
            role = UserType.Customer
        )
        _myStateLiveData.postValue(MyState.Success(
            userDetailModel
        ))

        /*preference.getString("access_token")?.let{
            userRepositoryImpl.getUserDetail(it)?.let {
                _myStateLiveData.postValue(MyState.Success(
                    it.toEntity()
                ))
            } ?: _myStateLiveData.postValue(MyState.Failure)
        } ?: _myStateLiveData.postValue(MyState.Failure)*/
    }
}