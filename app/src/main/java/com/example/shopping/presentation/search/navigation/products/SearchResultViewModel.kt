package com.example.shopping.presentation.search.navigation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.search.SearchRepositoryImpl
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val searchRepositoryImpl: SearchRepositoryImpl
) : BaseViewModel(){

    private var _searchStateLiveData = MutableLiveData<SearchResultState>(SearchResultState.UnInitialized)
    val searchResultStateLiveData: LiveData<SearchResultState> = _searchStateLiveData

    val searchItemListLiveData = MutableLiveData<List<ProductModel>>()

    override fun fetch(): Job = viewModelScope.launch {
    }

    fun getSearchProducts(keyword: String) = viewModelScope.launch(exceptionHandler){
        _searchStateLiveData.postValue(SearchResultState.Loading)

        val list = searchRepositoryImpl.getSearchProducts(keyword).mapIndexed { _, it ->
            it.toModel()
        }

        if(list.isNotEmpty()){
            searchItemListLiveData.value = list
            _searchStateLiveData.postValue(SearchResultState.Success)
        }
        else _searchStateLiveData.postValue(SearchResultState.Failure)
    }
}