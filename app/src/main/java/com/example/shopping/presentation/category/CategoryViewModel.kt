package com.example.shopping.presentation.category

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

class CategoryViewModel: BaseViewModel(){

    private var _categoryStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val categoryStateLiveData: LiveData<CategoryState> = _categoryStateLiveData

    val categoryListLiveData =  MutableLiveData<List<CategoryModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        _categoryStateLiveData.postValue(CategoryState.Loading)

        mockCategoriesCreate()
//        getAllCategories()

    }

    private fun mockCategoriesCreate(){

        val mockList = (0 until 10).map {
            CategoryModel(
                id = it.toString(),
                name = "category + $it"
            )
        }
        categoryListLiveData.value = mockList
        _categoryStateLiveData.postValue(CategoryState.Success(mockList))
    }

    /*private fun getAllCategories() = viewModelScope.launch{

        val list = getAllCategoriesUseCase().mapIndexed{ _, entity ->
            entity.toModel()
        }
        if(list.isNotEmpty()){
            _categoryStateLiveData.postValue(CategoryState.Success(list))
        }
        else _categoryStateLiveData.postValue(CategoryState.Failure)
    }*/

    /*fun getContent(category: String): Flow<PagingData<ProductResponse>> {
        return categoryRepository.getCategoryByProduct(category)
            .cachedIn(viewModelScope)
    }*/
}