package com.example.shopping.presentation.category.productsByCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsByCategoryViewModel(
    private val category_id: String,
    private val categoryRepositoryImpl: CategoryRepositoryImpl
): BaseViewModel(){

    private var _pbcStateLiveData = MutableLiveData<ProductsByCategoryState>(ProductsByCategoryState.UnInitialized)
    val pbcStateLiveData: LiveData<ProductsByCategoryState> = _pbcStateLiveData

    val productListLiveData =  MutableLiveData<List<ProductModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        _pbcStateLiveData.postValue(ProductsByCategoryState.Loading)

        getAllProductsByCategory()
    }

    /*fun settingList(){
        val mockList = (0 until 10).map {
            ProductModel(
                id = it.hashCode().toLong(),
                uid = "$it",
                name = "name$it",
                price = (it*1000).toLong(),
                description = null,
                image_url = ".../$it",
            )
        }

        productListLiveData.value = mockList
        _pbcStateLiveData.postValue(ProductsByCategoryState.Success(mockList))
    }*/

    private fun getAllProductsByCategory() = viewModelScope.launch {

        val list = categoryRepositoryImpl.getProductsByCategory(category_id).mapIndexed { _, it ->
            it.toModel()
        }

        if(list.isNotEmpty()){
            productListLiveData.value = list
            _pbcStateLiveData.postValue(ProductsByCategoryState.Success(list))
        }
        else _pbcStateLiveData.postValue(ProductsByCategoryState.Failure)
    }
}