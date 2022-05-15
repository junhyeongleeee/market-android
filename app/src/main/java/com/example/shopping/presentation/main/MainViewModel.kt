package com.example.shopping.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private var _mainStateLiveData = MutableLiveData<MainState>(MainState.UnInitialized)
    val mainStateLiveData: LiveData<MainState> = _mainStateLiveData

    override fun fetch(): Job = viewModelScope.launch {

    }
}