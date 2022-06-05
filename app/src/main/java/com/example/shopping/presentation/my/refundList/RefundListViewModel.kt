package com.example.shopping.presentation.my.refundList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.model.recyclerView.product.order.OrderModel
import com.example.shopping.model.recyclerView.product.order.RefundModel
import com.example.shopping.model.remote.order.OrderRefundCancelModel
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RefundListViewModel(
    private val preference: AppPreferenceManager,
    private val productRepositoryImpl: ProductRepositoryImpl,
) : BaseViewModel() {

    private var _orderListStateLiveData =
        MutableLiveData<RefundListState>(RefundListState.UnInitialized)
    val orderListStateLiveData: LiveData<RefundListState> = _orderListStateLiveData

    val orderItemListLiveData = MutableLiveData<List<RefundModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        getRefunds()
    }

    private fun getRefunds() = viewModelScope.launch(exceptionHandler) {

        _orderListStateLiveData.postValue(RefundListState.Loading)

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let { token ->
            productRepositoryImpl.getRefundList(token).let {

                if (it.isNotEmpty()) {

                    Log.e("orderList", it.toString())

                    orderItemListLiveData.value = it.mapIndexed { _, it ->
                        it.toModel()
                    }
                } else {
                    _orderListStateLiveData.postValue(RefundListState.Failure)
                }
            }
        } ?: _orderListStateLiveData.postValue(RefundListState.Failure)
    }

    /*fun requestCancel(order_id: String, refundCancelModel: OrderRefundCancelModel) =
        viewModelScope.launch(exceptionHandler) {
            _orderListStateLiveData.postValue(RefundListState.Loading)

            preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let { token ->
                productRepositoryImpl.requestRefundCancel(token, order_id, refundCancelModel)?.let {
                    Log.e("RefundEntity", it.toString())
                    _orderListStateLiveData.postValue(RefundListState.Success)

                    fetch()
                } ?: _orderListStateLiveData.postValue(RefundListState.Failure)
            } ?: _orderListStateLiveData.postValue(RefundListState.Failure)
        }*/

}