package com.example.shopping.presentation.my.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.MyState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserDetailViewModel : BaseViewModel(){

    private var _userDetailStateLiveData = MutableLiveData<UserDetailState>(UserDetailState.UnInitialized)
    val userDetailStateLiveData: LiveData<UserDetailState> = _userDetailStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _userDetailStateLiveData.postValue(UserDetailState.Loading)
    }


}