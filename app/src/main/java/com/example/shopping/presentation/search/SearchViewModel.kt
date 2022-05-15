package com.example.shopping.presentation.search

import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel(){
    override fun fetch(): Job = viewModelScope.launch {

    }
}