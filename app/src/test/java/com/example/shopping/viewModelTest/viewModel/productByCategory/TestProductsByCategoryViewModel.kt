package com.example.shopping.viewModelTest.viewModel.productByCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.data.repository.TestCategoryRepository
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.domain.usecases.GetAllCategoriesUseCase
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.category.CategoryState
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryState
import com.google.gson.annotations.Until
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TestProductsByCategoryViewModel(
    private val service: ApiService
): BaseViewModel(){

    private var _pbcStateLiveData = MutableLiveData<ProductsByCategoryState>(ProductsByCategoryState.UnInitialized)
    val pbcStateLiveData: LiveData<ProductsByCategoryState> = _pbcStateLiveData

    private var categoryRepository: TestCategoryRepository

    init {
        categoryRepository = TestCategoryRepository(service)
    }


    override fun fetch(): Job = viewModelScope.launch {
        _pbcStateLiveData.postValue(ProductsByCategoryState.Loading)

    }

    fun getCategories(category_id: String) = viewModelScope.launch {
        val list = categoryRepository.getProductsByCategory(category_id)

        if(list.isNotEmpty()){
            /*_pbcStateLiveData.postValue(ProductsByCategoryState.Success(list.mapIndexed{ _, entity ->
                entity.toEntity()
            }))*/
        }
        else _pbcStateLiveData.postValue(ProductsByCategoryState.Failure)
    }

}