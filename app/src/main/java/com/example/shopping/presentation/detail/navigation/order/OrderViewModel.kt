package com.example.shopping.presentation.detail.navigation.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderViewModel(
    private val preference: AppPreferenceManager,
    private val productRepositoryImpl: ProductRepositoryImpl
): BaseViewModel() {

    private var _orderStateLiveData = MutableLiveData<OrderState>(OrderState.UnInitialized)
    val orderStateLiveData: LiveData<OrderState> = _orderStateLiveData

    override fun fetch(): Job = viewModelScope.launch {

    }

    fun orderProduct(orderList: List<OrderItemEntity>) = viewModelScope.launch {
        _orderStateLiveData.postValue(OrderState.Loading)

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let { token ->
            productRepositoryImpl.orderProduct(token, orderList)?.let {
                Log.e("orderProduct", it.toString())
                _orderStateLiveData.postValue(OrderState.Success(it))
            }?: kotlin.run {
                _orderStateLiveData.postValue(OrderState.Failure)
            }
        } ?: kotlin.run {
            Log.e("Error Token", "Error!!")
            _orderStateLiveData.postValue(OrderState.Failure)
        }

    }
}