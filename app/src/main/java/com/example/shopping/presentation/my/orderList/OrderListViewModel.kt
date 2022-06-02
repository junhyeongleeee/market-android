package com.example.shopping.presentation.my.orderList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.HomeState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderListViewModel : BaseViewModel(){

    private var _alarmStateLiveData = MutableLiveData<OrderListState>(OrderListState.UnInitialized)
    val alarmStateLiveData: LiveData<OrderListState> = _alarmStateLiveData

    val orderItemListLiveData =  MutableLiveData<List<OrderModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        mockOrderItemModel()
    }

    private fun mockOrderItemModel() {
        _alarmStateLiveData.postValue(OrderListState.Loading)

        val mockList = (0 until 10).map {
            OrderModel(
                id = it.hashCode().toLong(),
                uid = it.toString(),
                customer_id = "customer_id $it",
                total_price = it*1000,
                status = "status",
                ordered_at = "ordered_at",
                updated_at = "updated_at",
                items = (0 until 10).map { count ->
                    OrderItemEntity(
                        uid = "$count",
                        order_id = "order_id $count",
                        product_id = "product_id $count",
                        product_name = "product_name $count",
                        product_price = count*1000,
                        count = count
                    )
                }
            )
        }
        orderItemListLiveData.value = mockList
    }
}