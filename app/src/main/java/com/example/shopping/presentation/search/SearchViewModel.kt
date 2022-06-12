package com.example.shopping.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.search.SearchRepositoryImpl
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel: BaseViewModel(){

    override fun fetch(): Job = viewModelScope.launch {
    }

    fun getSearchProducts(keyword: String) = viewModelScope.launch(exceptionHandler){

    }
}