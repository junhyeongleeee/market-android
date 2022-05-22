package com.example.shopping.presentation.category.productsByCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.domain.usecases.GetAllCategoriesUseCase
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.base.BaseViewModel
import com.google.gson.annotations.Until
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductsByCategoryViewModel: BaseViewModel(){

    private var _pbcStateLiveData = MutableLiveData<ProductsByCategoryState>(ProductsByCategoryState.UnInitialized)
    val pbcStateLiveData: LiveData<ProductsByCategoryState> = _pbcStateLiveData

    val categoryListLiveData =  MutableLiveData<List<CategoryModel>>()

    override fun fetch(): Job = viewModelScope.launch {


    }
}