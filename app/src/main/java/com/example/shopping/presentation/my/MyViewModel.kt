package com.example.shopping.presentation.my

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel : BaseViewModel(){
    override fun fetch(): Job = viewModelScope.launch {

    }
}