package com.example.shopping.presentation.detail.navigation.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.model.product.order.OrderListModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.RemoteState
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

class OrderViewModel(
    private val preference: AppPreferenceManager,
    private val productRepositoryImpl: ProductRepositoryImpl
): BaseViewModel() {

    private var _orderStateLiveData = MutableLiveData<OrderState>(OrderState.UnInitialized)
    val orderStateLiveData: LiveData<OrderState> = _orderStateLiveData

    private val _fetchState = MutableLiveData<Pair<Throwable, RemoteState>>()
    val fetchState : LiveData<Pair<Throwable, RemoteState>>
        get() = _fetchState

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()

        when(throwable){
            is SocketException -> _fetchState.postValue(Pair(throwable, RemoteState.BAD_INTERNET))
            is HttpException -> _fetchState.postValue(Pair(throwable, RemoteState.PARSE_ERROR))
            is UnknownHostException -> _fetchState.postValue(Pair(throwable, RemoteState.WRONG_CONNECTION))
            else -> _fetchState.postValue(Pair(throwable, RemoteState.FAIL))
        }
    }

    override fun fetch(): Job = viewModelScope.launch {

    }

    fun orderProduct(orderList: OrderListModel) = viewModelScope.launch(exceptionHandler) {
        _orderStateLiveData.postValue(OrderState.Loading)

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let { token ->
            productRepositoryImpl.orderProduct(token, orderList)?.let {
                Log.e("OrderViewModel orderProduct", it.toString())
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