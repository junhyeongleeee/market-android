package com.example.shopping.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.alarm.AlarmState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel : BaseViewModel() {

    private var _myStateLiveData = MutableLiveData<MyState>(MyState.UnInitialized)
    val myStateLiveData: LiveData<MyState> = _myStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _myStateLiveData.postValue(MyState.Loading)
    }
    // TODO : User Data Fetching 필요
    fun getUserData(){
        _myStateLiveData.postValue(MyState.Failure)
    }
}