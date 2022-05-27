package com.example.shopping.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.type.CellType
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(){


    private var _homeStateLiveData = MutableLiveData<HomeState>(HomeState.UnInitialized)
    val homeStateLiveData: LiveData<HomeState> = _homeStateLiveData

    val productListLiveData =  MutableLiveData<List<ProductModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        _homeStateLiveData.postValue(HomeState.Loading)
    }

    fun settingList(){
        val mockList = (0 until 10).map {
            ProductModel(
                id = it.hashCode().toLong(),
                type = CellType.PRODUCT_OF_SEARCH_CELL,
                uid = "$it",
                name = "name$it",
                price = (it*1000).toLong(),
                image_url = ".../$it",
            )
        }
        productListLiveData.value = mockList
        _homeStateLiveData.postValue(HomeState.Success(mockList))
    }
}