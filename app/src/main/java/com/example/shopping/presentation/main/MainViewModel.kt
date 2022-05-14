package com.example.shopping.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {
    override fun fetch(): Job = viewModelScope.launch {  }
}