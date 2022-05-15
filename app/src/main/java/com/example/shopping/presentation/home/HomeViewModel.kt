package com.example.shopping.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(){
    override fun fetch(): Job = viewModelScope.launch {

    }
}