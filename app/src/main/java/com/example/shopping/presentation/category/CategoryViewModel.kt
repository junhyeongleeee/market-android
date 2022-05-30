package com.example.shopping.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.repository.category.CategoryRepository
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepositoryImpl: CategoryRepositoryImpl,
) : BaseViewModel() {

    private var _categoryStateLiveData = MutableLiveData<CategoryState>(CategoryState.UnInitialized)
    val categoryStateLiveData: LiveData<CategoryState> = _categoryStateLiveData

    val categoryListLiveData = MutableLiveData<List<CategoryModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        _categoryStateLiveData.postValue(CategoryState.Loading)

        getAllCategories()
    }

    fun settingList() {
        val mockList = (0 until 10).map {
            CategoryModel(
                id = it.toLong(),
                category_id = it.toString(),
                name = "category + $it"
            )
        }
        categoryListLiveData.value = mockList
    }

    private fun getAllCategories() = viewModelScope.launch {

        val list = categoryRepositoryImpl.getCategories().mapIndexed { _, entity ->
            entity.toModel()
        }

        if (list.isNotEmpty()) {
            categoryListLiveData.value = list
            _categoryStateLiveData.postValue(CategoryState.Success(list))
        } else _categoryStateLiveData.postValue(CategoryState.Failure)
    }

    /*fun getContent(category: String): Flow<PagingData<ProductResponse>> {
        return categoryRepository.getCategoryByProduct(category)
            .cachedIn(viewModelScope)
    }*/
}