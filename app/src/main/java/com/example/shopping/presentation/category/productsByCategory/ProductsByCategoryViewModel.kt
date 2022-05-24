package com.example.shopping.presentation.category.productsByCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsByCategoryViewModel: BaseViewModel(){

    private var _pbcStateLiveData = MutableLiveData<ProductsByCategoryState>(ProductsByCategoryState.UnInitialized)
    val pbcStateLiveData: LiveData<ProductsByCategoryState> = _pbcStateLiveData

    val productListLiveData =  MutableLiveData<List<ProductModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        _pbcStateLiveData.postValue(ProductsByCategoryState.Loading)
    }

    fun settingList(){
        val mockList = (0 until 10).map {
            ProductModel(
                id = it.hashCode().toLong(),
                uid = "$it",
                name = "name$it",
                price = (it*1000).toLong(),
                image_url = ".../$it",
            )
        }
        productListLiveData.value = mockList
        _pbcStateLiveData.postValue(ProductsByCategoryState.Success(mockList))
    }
}