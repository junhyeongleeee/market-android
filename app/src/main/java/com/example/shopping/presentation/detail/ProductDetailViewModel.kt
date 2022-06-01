package com.example.shopping.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.repository.category.CategoryRepository
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductDetailViewModel: BaseViewModel() {

    private var _productDetailStateLiveData = MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailStateLiveData: LiveData<ProductDetailState> = _productDetailStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _productDetailStateLiveData.postValue(ProductDetailState.Loading)

    }
}