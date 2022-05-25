package com.example.shopping.presentation.home.alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.HomeState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlarmViewModel : BaseViewModel(){

    private var _alarmStateLiveData = MutableLiveData<AlarmState>(AlarmState.UnInitialized)
    val alarmStateLiveData: LiveData<AlarmState> = _alarmStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _alarmStateLiveData.postValue(AlarmState.Loading)

        //
        alarmCheck()
    }

    //TODO 알람이 존재하는지 체크해야 함.
    fun alarmCheck() {
        _alarmStateLiveData.postValue(AlarmState.AlarmExistence.Failure)
    }
}