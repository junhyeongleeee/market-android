package com.example.shopping.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.home.HomeRepositoryImpl
import com.example.shopping.model.type.CellType
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepositoryImpl: HomeRepositoryImpl
) : BaseViewModel(){

    private var _homeStateLiveData = MutableLiveData<HomeState>(HomeState.UnInitialized)
    val homeStateLiveData: LiveData<HomeState> = _homeStateLiveData

    val whatProductListLiveData =  MutableLiveData<List<ProductModel>>()
    val hotProductListLiveData =  MutableLiveData<List<ProductModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        getHomeData()
    }

    fun settingList(){
        val mockList = (0 until 10).map {
            ProductModel(
                id = it.hashCode().toLong(),
                type = CellType.PRODUCT_OF_SEARCH_CELL,
                uid = "$it",
                name = "name$it",
                price = it*1000,
                image_url = ".../$it",
            )
        }
        whatProductListLiveData.value = mockList
    }

    private fun getHomeData() = viewModelScope.launch(exceptionHandler){

        _homeStateLiveData.postValue(HomeState.Loading)

        val recommendList = homeRepositoryImpl.getHome()

        if(recommendList.isNotEmpty()){
            val whatList = recommendList[0].products.mapIndexed { _, entity ->
                entity.toModel()
            }
            val hotList = recommendList[1].products.mapIndexed { _, entity ->
                entity.toModel()
            }

            Log.e("whatList", whatList.toString())

            whatProductListLiveData.value = whatList
            hotProductListLiveData.value = hotList

            _homeStateLiveData.postValue(HomeState.Success(
                whatList, hotList
            ))
        } else _homeStateLiveData.postValue(HomeState.Failure)
    }
}