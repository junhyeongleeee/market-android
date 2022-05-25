package com.example.shopping.presentation.my.auth.navigation.register

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel: BaseViewModel() {
    override fun fetch(): Job = viewModelScope.launch {

    }
}