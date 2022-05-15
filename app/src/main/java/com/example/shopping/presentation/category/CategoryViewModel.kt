package com.example.shopping.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.main.MainState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel(){


    private var _mainStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val mainStateLiveData: LiveData<CategoryState> = _mainStateLiveData

    override fun fetch(): Job = viewModelScope.launch {

    }
}