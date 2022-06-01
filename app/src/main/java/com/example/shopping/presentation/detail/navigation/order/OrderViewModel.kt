package com.example.shopping.presentation.detail.navigation.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderViewModel(
    private val productRepositoryImpl: ProductRepositoryImpl
): BaseViewModel() {

    private var _orderStateLiveData = MutableLiveData<OrderState>(OrderState.UnInitialized)
    val orderStateLiveData: LiveData<OrderState> = _orderStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
    }

}