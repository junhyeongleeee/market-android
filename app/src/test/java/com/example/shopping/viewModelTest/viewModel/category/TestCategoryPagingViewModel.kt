package com.example.shopping.viewModelTest.viewModel.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.data.response.product.ProductResponse
import com.example.shopping.presentation.category.CategoryState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class TestCategoryPagingViewModel(
    private val service: ApiService
): ViewModel() {

    private var _mainStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val mainStateLiveData: LiveData<CategoryState> = _mainStateLiveData

    private var _pagingLiveData = MutableLiveData<Flow<PagingData<ProductResponse>>>()

    suspend fun fetch(): Job = viewModelScope.launch {
        _mainStateLiveData.postValue(CategoryState.Loading)

        var list: List<CategoryEntity> = listOf()

        runBlocking {

            val response = service.getAllCategory()

            if (response.isSuccessful) {
                list = response?.body()?.mapIndexed { index, it ->
                    CategoryEntity(
                        id = it.id,
                        name = it.id
                    )
                } ?: listOf()
            }
        }

        if(list.isNotEmpty()){
            _mainStateLiveData.postValue(CategoryState.Success(list.mapIndexed{ _, entity ->
               entity.toModel()
            }))
        }
        else _mainStateLiveData.postValue(CategoryState.Failure)
    }


}