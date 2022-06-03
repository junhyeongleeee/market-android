package com.example.shopping.presentation.my.orderList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.home.HomeState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val preference: AppPreferenceManager,
    private val productRepositoryImpl: ProductRepositoryImpl
) : BaseViewModel(){

    private var _orderListStateLiveData = MutableLiveData<OrderListState>(OrderListState.UnInitialized)
    val orderListStateLiveData: LiveData<OrderListState> = _orderListStateLiveData

    val orderItemListLiveData =  MutableLiveData<List<OrderModel>>()

    override fun fetch(): Job = viewModelScope.launch {
        mockOrderItemModel()
//        getOrders()
    }

    private fun mockOrderItemModel() {
        _orderListStateLiveData.postValue(OrderListState.Loading)

        val mockList = (0 until 10).map {
            OrderModel(
                id = it.hashCode().toLong(),
                uid = it.toString(),
                customer_id = "customer_id $it",
                total_price = it*1000,
                status = "status",
                ordered_at = "2022.06.$it",
                updated_at = "updated_at",
                items = (0 until 10).map { count ->
                    OrderItemEntity(
                        uid = "$count",
                        order_id = "order_id $count",
                        product_id = "product_id $count",
                        product_name = "product_name $count",
                        product_price = count*1000,
                        product_image_url = null,
                        count = count
                    )
                }
            )
        }
        orderItemListLiveData.value = mockList
    }

    private fun getOrders() = viewModelScope.launch {

        _orderListStateLiveData.postValue(OrderListState.Loading)

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let { token ->
            productRepositoryImpl.getOrderList(token).let {

                if(it.isNotEmpty()){


                    orderItemListLiveData.value = it.mapIndexed { _, entity ->
                        OrderModel(
                            id = entity.uid.hashCode().toLong(),
                            uid = entity.uid,
                            customer_id = entity.customer_id,
                            total_price = entity.total_price,
                            status = entity.status,
                            ordered_at = entity.ordered_at,
                            updated_at = entity.updated_at,
                            items = entity.items
                        )
                    }
//                    _orderListStateLiveData.postValue(OrderListState.Success)
                }
                else{
                    _orderListStateLiveData.postValue(OrderListState.Failure)
                }
            }
        } ?: _orderListStateLiveData.postValue(OrderListState.Failure)
    }

}