package com.example.shopping.presentation.my.auth.navigation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.auth.navigation.login.LoginState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel: BaseViewModel() {

    private var _registerStateLiveData = MutableLiveData<RegisterState>(RegisterState.UnInitialized)
    val registerStateLiveData: LiveData<RegisterState> = _registerStateLiveData

    override fun fetch(): Job = viewModelScope.launch {

    }
}