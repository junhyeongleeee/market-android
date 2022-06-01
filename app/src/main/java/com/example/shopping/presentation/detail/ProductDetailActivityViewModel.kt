package com.example.shopping.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductDetailActivityViewModel: BaseViewModel() {

    override fun fetch(): Job = viewModelScope.launch {
    }
}