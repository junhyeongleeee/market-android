package com.example.shopping.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.usecases.GetAllCategoriesUseCase
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.main.MainState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel(){

    private var _mainStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val mainStateLiveData: LiveData<CategoryState> = _mainStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        _mainStateLiveData.postValue(CategoryState.Loading)

        val list = getAllCategoriesUseCase()
        if(list.isNotEmpty()){
            _mainStateLiveData.postValue(CategoryState.Success(list))
        }
        else _mainStateLiveData.postValue(CategoryState.Failure)
    }
}