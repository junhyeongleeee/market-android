package com.example.shopping.viewModelTest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.category.CategoryEntity
import com.example.shopping.data.remote.service.ApiService
import com.example.shopping.presentation.category.CategoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestCategoryViewModel(
    private val service: ApiService
): ViewModel() {

    private var _mainStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val mainStateLiveData: LiveData<CategoryState> = _mainStateLiveData

    suspend fun fetch(): Job = viewModelScope.launch {
        _mainStateLiveData.postValue(CategoryState.Loading)

        var list: List<CategoryEntity> = listOf()

        withContext(Dispatchers.IO){
            val response = service.getAllCategory()
            if(response.isSuccessful){
                list = response?.body()?.mapIndexed { index, it ->
                    CategoryEntity(
                        id = it.id,
                        name = it.id
                    )
                } ?: listOf()
            }
        }

        if(list.isNotEmpty()){
            _mainStateLiveData.postValue(CategoryState.Success(list))
        }
        else _mainStateLiveData.postValue(CategoryState.Failure)
    }
}