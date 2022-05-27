package com.example.shopping.presentation.my.auth.navigation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.MyState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {

    private var _loginStateLiveData = MutableLiveData<LoginState>(LoginState.UnInitialized)
    val loginStateLiveData: LiveData<LoginState> = _loginStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _loginStateLiveData.postValue(LoginState.Loading)
    }

}