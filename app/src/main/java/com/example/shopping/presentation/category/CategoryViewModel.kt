package com.example.shopping.presentation.category

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel(){
    override fun fetch(): Job = viewModelScope.launch {

    }
}